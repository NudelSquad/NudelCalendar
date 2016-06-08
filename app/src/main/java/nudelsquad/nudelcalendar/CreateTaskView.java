package nudelsquad.nudelcalendar;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;

import net.margaritov.preference.colorpicker.ColorPickerDialog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Marco on 04.05.2016.
 */
public class CreateTaskView extends Fragment {

    private View rootView;
    private EditText taskName;
    private EditText taskDate;
    private EditText taskColor;
    private EditText taskText;
    private CheckBox taskReminder;
    private DatePickerDialog  eventDatePickerDialog;
    private DateFormat dateFormater;
    private ColorPickerDialog colPicker;
    int color = Color.parseColor("#33b5e5");
    private int fromEvent = -1;
    private Task taskToUpdate = null;


    public CreateTaskView(int fromEvent) {
        this.fromEvent = fromEvent;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.create_task_fragement, container, false);
        if(fromEvent > 0){
            DBHandler dbh = new DBHandler(rootView.getContext());
            taskToUpdate = dbh.getTask(fromEvent);
        }
        findViewsById();
        dateFormater = new SimpleDateFormat("dd-MM-yyyy", Locale.GERMAN);

        taskColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colPicker = new ColorPickerDialog(rootView.getContext(), color);
                colPicker.setAlphaSliderVisible(true);
                colPicker.setHexValueEnabled(true);
                colPicker.setTitle(R.string.choose_color);
                colPicker.setOnColorChangedListener(new ColorPickerDialog.OnColorChangedListener() {
                    @Override
                    public void onColorChanged(int i) {
                        color = i;
                        taskColor.setText("#" + Integer.toHexString(i));
                        taskColor.setBackgroundColor(i);
                    }
                });
                colPicker.show();
            }
        });

        taskDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar newCalendar = Calendar.getInstance();
                eventDatePickerDialog = new DatePickerDialog(rootView.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);
                        taskDate.setText(dateFormater.format(newDate.getTime()));
                    }
                }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
                eventDatePickerDialog.show();
            }
        });

        Button saveTaskButton = (Button) rootView.findViewById(R.id.saveTaskBtn);
        saveTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(taskName.toString().isEmpty() || taskDate.toString().isEmpty()){
                    AlertDialog.Builder alert1 = new AlertDialog.Builder(rootView.getContext());
                    alert1.setMessage(R.string.missing_field)
                            .setCancelable(false)
                            .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert2 = alert1.create();
                    alert2.setTitle("ALERT");
                    alert2.show();
                } else {
                    AlertDialog.Builder alert1 = new AlertDialog.Builder(rootView.getContext());
                    alert1.setMessage(R.string.save_task)
                            .setCancelable(false)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    saveTask();
                                }
                            })
                            .setNegativeButton(R.string.ok, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert3 = alert1.create();
                    alert3.setTitle(R.string.add_task);
                    alert3.show();
                }
            }
        });


        // brings you back to previous screen. it can be taskwall or CreateEvent
        Button discardTaskButton = (Button) rootView.findViewById(R.id.discardTaskBtn);
        discardTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.main_frame, new CreateEventView(), "NewFragmentTag");
                ft.commit();
            }
        });

        return rootView;
    }

    private void saveTask() {
        String name = taskName.getText().toString();
        String datum = taskDate.getText().toString();
        String text = taskText.getText().toString();
        String Col = taskColor.getText().toString();
        int c = 0;
        try{
            c = Integer.parseInt(Col);
        }
        catch (Exception e){
            c = Color.parseColor(Col);
        }


        Task t = new Task(name, datum, text, c, -1, false);
        DBHandler dbh = new DBHandler(rootView.getContext());
        if(fromEvent == -1){
            dbh.addTask(t);
            final FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.main_frame, new TaskBoard(), "NewFragmentTag");
            ft.commit();
        }
        else if(fromEvent == 0){
            Task.getOpenTasks().add(t);
            final FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.main_frame, new CreateEventView(), "NewFragmentTag");
            ft.commit();
        }
        else {
            Task upt = new Task(taskToUpdate.getTASK_ID(), name, datum, text, c, taskToUpdate.getTASK_EVENTID(), false);
            dbh.updateTask(upt);
            final FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.main_frame, new TaskBoard(), "NewFragmentTag");
            ft.commit();
        }

    }

    private void findViewsById(){
        taskName = (EditText) rootView.findViewById(R.id.taskName);
        taskDate = (EditText) rootView.findViewById(R.id.taskDate);
        taskDate.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.calendar), null);
        taskDate.setInputType(InputType.TYPE_NULL);
        taskDate.requestFocus();
        taskColor = (EditText) rootView.findViewById(R.id.taskColor);
        taskColor.setInputType(InputType.TYPE_NULL);
        taskColor.requestFocus();
        taskText = (EditText) rootView.findViewById(R.id.taskText);
        taskReminder = (CheckBox) rootView.findViewById(R.id.taskReminder);
        if(taskToUpdate != null) {
            taskName.setText(taskToUpdate.getTASK_NAME());
            taskDate.setText(taskToUpdate.getTASK_DATUM());
            taskColor.setText(taskToUpdate.getTASK_COLOR() + "");
            taskText.setText(taskToUpdate.getTASK_TEXT());
            taskReminder.setChecked(taskToUpdate.getTASK_CHECKED());
        }
    }

}
