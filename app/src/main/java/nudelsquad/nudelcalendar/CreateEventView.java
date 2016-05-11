package nudelsquad.nudelcalendar;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.InputType;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import net.margaritov.preference.colorpicker.ColorPickerDialog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Marco on 04.05.2016.
 */

public class CreateEventView extends Fragment implements View.OnClickListener {
    private View rootView;
    private EditText edtTextEventDate;
    private  EditText edtTextBegin;
    private  EditText edtTextEnd;
    private EditText eventName;
    private EditText eventType;
    private EditText eventPlace;
    private DatePickerDialog eventDatePickerDialog;
    private DateFormat dateFormater;
    private TimePickerDialog timePick1;
    private  TimePickerDialog timePick2;
    private EditText colorText;
    private ColorPickerDialog colPicker;
    private ListView lvTasks;
    ArrayList<Task> tasks;
    //private Spinner days;
    int color = Color.parseColor("#33b5e5");
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.create_event_fragment, container, false);
        dateFormater = new SimpleDateFormat("dd-MM-yyyy", Locale.GERMAN);
        findViewsById();
        setDateTimeField();
        setBeginTimeField();
        setEndTimeField();
        setColorField();

        Button addTaskButton = (Button) rootView.findViewById(R.id.addTaskAct);
        addTaskButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.main_frame, new CreateTaskView(1), "NewFragmentTag");
                ft.commit();
            }
        });

        Button discardButton = (Button) rootView.findViewById(R.id.button);
        discardButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                edtTextBegin.setText("");
                edtTextEventDate.setText("");
                edtTextEnd.setText("");
                colorText.setText("");
                eventName.setText("");
                eventType.setText("");
                eventPlace.setText("");
            }
        });

        Button addNewEvent = (Button) rootView.findViewById(R.id.button2);
        addNewEvent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (edtTextBegin.getText().toString().isEmpty() ||
                        eventName.getText().toString().isEmpty() ||
                        edtTextEventDate.getText().toString().isEmpty() ||
                        edtTextEnd.getText().toString().isEmpty() ||
                        eventType.getText().toString().isEmpty()) {

                    AlertDialog.Builder alert1 = new AlertDialog.Builder(rootView.getContext());
                    alert1.setMessage("Missing required data!")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
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
                    alert1.setMessage("Save Event??!")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    saveEvent();
                                    Toast.makeText(rootView.getContext(),"Saved", Toast.LENGTH_SHORT).show();
                                    // Add function to save into Database
                                }
                            })
                            .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert3 = alert1.create();
                    alert3.setTitle("ADD EVENT");
                    alert3.show();
                }
            }
        });

        return rootView;
    }



    private void findViewsById(){
        edtTextBegin = (EditText) rootView.findViewById(R.id.begin);
        edtTextBegin.setInputType(InputType.TYPE_NULL);
        edtTextBegin.requestFocus();

        eventName = (EditText) rootView.findViewById(R.id.eventNameText);

        edtTextEnd = (EditText) rootView.findViewById(R.id.editText6);
        edtTextEnd.setInputType(InputType.TYPE_NULL);
        edtTextEnd.requestFocus();

        edtTextEventDate = (EditText) rootView.findViewById(R.id.textFromDate);
        edtTextEventDate.setInputType(InputType.TYPE_NULL);
        edtTextEventDate.requestFocus();

        colorText = (EditText) rootView.findViewById(R.id.colorEditText);
        colorText.setInputType(InputType.TYPE_NULL);
        colorText.requestFocus();

        eventType = (EditText) rootView.findViewById(R.id.eventTypeText);
        eventPlace = (EditText) rootView.findViewById(R.id.placeText);
        //days = (Spinner) rootView.findViewById(R.id.days);

        lvTasks = (ListView) rootView.findViewById(R.id.tasks_list);
        tasks = Task.getOpenTasks();
        String items[] = new String[tasks.size()];
        for(int i = 0; i < tasks.size(); i++) {
            items[i] = tasks.get(i).getTASK_NAME();
            Log.e("Task", items[i]);
        }
        ArrayAdapter adp = new ArrayAdapter(rootView.getContext(), android.R.layout.simple_list_item_1, items);
        lvTasks.setAdapter(adp);


    }

    private void setDateTimeField(){
        edtTextEventDate.setOnClickListener(this);
        //   Calendar newDate = Calendar.getInstance();
        Calendar newCalendar = Calendar.getInstance();
        eventDatePickerDialog = new DatePickerDialog(rootView.getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                edtTextEventDate.setText(dateFormater.format(newDate.getTime()));
            }
        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }

    private void setBeginTimeField(){
        edtTextBegin.setOnClickListener(this);
        Calendar calendar = Calendar.getInstance();
        timePick1 = new TimePickerDialog(rootView.getContext(), new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void  onTimeSet(TimePicker view, int hourOfDay, int minute){
                Calendar newTime = Calendar.getInstance();
                newTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                newTime.set(Calendar.MINUTE, minute);
                String timeString = DateUtils.formatDateTime(rootView.getContext(), newTime.getTimeInMillis(),DateUtils.FORMAT_SHOW_TIME);
                edtTextBegin.setText(timeString);
            }
        },calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), android.text.format.DateFormat.is24HourFormat(rootView.getContext()));
    }


    private void setEndTimeField(){
        edtTextEnd.setOnClickListener(this);
        Calendar calendar = Calendar.getInstance();
        timePick2 = new TimePickerDialog(rootView.getContext(), new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void  onTimeSet(TimePicker view, int hourOfDay, int minute){
                Calendar newTime = Calendar.getInstance();
                newTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                newTime.set(Calendar.MINUTE, minute);
                String timeString = DateUtils.formatDateTime(rootView.getContext(), newTime.getTimeInMillis(),DateUtils.FORMAT_SHOW_TIME);
                edtTextEnd.setText(timeString);
            }
        },calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), android.text.format.DateFormat.is24HourFormat(rootView.getContext()));
    }

    private void setColorField(){
        colorText.setOnClickListener(this);
        colPicker = new ColorPickerDialog(rootView.getContext(),color);
        colPicker.setAlphaSliderVisible(true);
        colPicker.setHexValueEnabled(true);
        colPicker.setTitle("Farbe auswählen");
        colPicker.setOnColorChangedListener(new ColorPickerDialog.OnColorChangedListener(){
            @Override
            public void onColorChanged(int i){
                color = i;
                colorText.setText("#" + Integer.toHexString(i));
                colorText.setBackgroundColor(i);
            }
        });}

    public void onClick(View view){
        if(view == edtTextEventDate){
            eventDatePickerDialog.show();
        }
        else if(view == edtTextBegin ){
            timePick1.show();
        }
        else if(view == edtTextEnd){
            timePick2.show();
        }
        else if(view == colorText){
            colPicker.show();
        }
    }

    private void saveEvent() {
        String Start = edtTextBegin.getText().toString();
        String End = edtTextEnd.getText().toString();
        String Datum = edtTextEventDate.getText().toString();
        String Name = eventName.getText().toString();
        String Type = eventType.getText().toString();
        String Loc = eventPlace.getText().toString();
        String Col = colorText.getText().toString();
        int c = Color.parseColor(Col);

        Event e = new Event(Name, Start, End, Datum, Type, Loc, c);

        DBHandlerEvent dbh = new DBHandlerEvent(rootView.getContext());
        dbh.addEvent(e);

        int evid = dbh.getEventsCount();
        DBHandlerTask dbht = new DBHandlerTask(rootView.getContext());
        for(int i = 0; i < tasks.size(); i++){
            tasks.get(i).setTASK_EVENTID(evid);
            dbht.addTask(tasks.get(i));
        }

        Task.getOpenTasks().clear();
    }
}
