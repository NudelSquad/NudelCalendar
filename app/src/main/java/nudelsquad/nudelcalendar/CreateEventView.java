package nudelsquad.nudelcalendar;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
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
import android.widget.ImageButton;

import android.widget.TimePicker;
import android.widget.Toast;

import net.margaritov.preference.colorpicker.ColorPickerDialog;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Marco on 04.05.2016.
 * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
 * <uses-permission android:name="android.permission.RECORD_AUDIO" />
 */

public class CreateEventView extends Fragment implements View.OnClickListener {
    private static final String TAG = "RECORDER";
    private View rootView;
    private EditText edtTextEventDate;
    private EditText edtTextBegin;
    private EditText edtTextEnd;
    private EditText eventName;
    private EditText eventType;
    private EditText eventPlace;
    private DatePickerDialog eventDatePickerDialog;
    private DateFormat dateFormater;
    private TimePickerDialog timePick1;
    private TimePickerDialog timePick2;
    private EditText colorText;
    private ImageButton btnRecord;
    private ImageButton btnPlay;
    private ColorPickerDialog colPicker;

    private ListView lvTasks;
    ArrayList<Task> tasks;

    private MediaRecorder mRecorder = null;
    private MediaPlayer mPlayer = null;
    private Button discardButton;
    private static boolean mStartRecording = true;
    private static boolean mStartPlaying = true;
    private DBHandler dbHandler;

    private int eventID = 0;
    private Event eventToUpdate = null;


    //private Spinner days;
    int color = Color.parseColor("#33b5e5");
    private String mFileName;
    private boolean hasRecorded=false;



    public CreateEventView(int EventID) {
        this.eventID = EventID;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.create_event_fragment, container, false);
        dateFormater = new SimpleDateFormat("dd-MM-yyyy", Locale.GERMAN);
        if(eventID != 0){
            DBHandler dbh = new DBHandler(rootView.getContext());
            eventToUpdate = dbh.getEvent(eventID);
        }
        findViewsById();
        setDateTimeField();
        setBeginTimeField();
        setEndTimeField();
        setColorField();
        dbHandler=new DBHandler(getContext());




        mFileName = Environment.getExternalStorageDirectory().toString() + getString(R.string.appid);
        File file = new File(mFileName);

        try{
            file.mkdirs();
        }
        catch(SecurityException se){
            //handle it
        }

        mFileName += System.currentTimeMillis() + ".3gp";



        Log.i(TAG, mFileName);

        Button addTaskButton = (Button) rootView.findViewById(R.id.addTaskAct);
        addTaskButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.main_frame, new CreateTaskView(0), "NewFragmentTag");
                ft.commit();
            }
        });


        discardButton = (Button) rootView.findViewById(R.id.btn_discard);
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

        Button addNewEvent = (Button) rootView.findViewById(R.id.btn_add_event);
        addNewEvent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (edtTextBegin.getText().toString().isEmpty() ||
                        eventName.getText().toString().isEmpty() ||
                        edtTextEventDate.getText().toString().isEmpty() ||
                        edtTextEnd.getText().toString().isEmpty() ||
                        eventType.getText().toString().isEmpty() ||
                        colorText.getText().toString().isEmpty()) {





                    AlertDialog.Builder alert1 = new AlertDialog.Builder(rootView.getContext());
                    alert1.setMessage(R.string.missing_data_alert)
                            .setCancelable(false)
                            .setPositiveButton(R.string.cancel, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                } else {
                    AlertDialog.Builder alert1 = new AlertDialog.Builder(rootView.getContext());

                    alert1.setMessage(R.string.save_event_question)
                            .setCancelable(false)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    saveEvent();
                                    Toast.makeText(rootView.getContext(), R.string.saved, Toast.LENGTH_SHORT).show();
                                    final FragmentTransaction ft = getFragmentManager().beginTransaction();
                                    ft.replace(R.id.main_frame, new DayList(), "NewFragmentTag");
                                    ft.commit();
                                }
                            })

                            .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert3 = alert1.create();
                    alert3.setTitle(getString(R.string.event_added));
                    alert3.show();


                }
            }
        });

        btnRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.RECORD_AUDIO)!= PackageManager.PERMISSION_GRANTED ||
                        ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(getActivity().getBaseContext(), R.string.no_permissions, Toast.LENGTH_SHORT).show();

                    return;
                }

                onRecord(mStartRecording);
                mStartRecording = !mStartRecording;
            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPlay(mStartPlaying);
                mStartPlaying = !mStartPlaying;
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

        btnRecord = (ImageButton) rootView.findViewById(R.id.btn_record);
        btnPlay = (ImageButton) rootView.findViewById(R.id.btn_play);


        lvTasks = (ListView) rootView.findViewById(R.id.tasks_list);
        tasks = Task.getOpenTasks();
        String items[] = new String[tasks.size()];
        for(int i = 0; i < tasks.size(); i++) {
            items[i] = tasks.get(i).getTASK_NAME();
            Log.e("Task", items[i]);
        }
        ArrayAdapter adp = new ArrayAdapter(rootView.getContext(), android.R.layout.simple_list_item_1, items);
        lvTasks.setAdapter(adp);

        if(eventToUpdate != null){
            eventName.setText(eventToUpdate.getEVENT_NAME());
            edtTextEventDate.setText(eventToUpdate.getEVENT_DATUM());
            edtTextBegin.setText(eventToUpdate.getEVENT_START());
            edtTextEnd.setText(eventToUpdate.getEVENT_END());
            eventPlace.setText(eventToUpdate.getEVENT_LOCATION());
            eventType.setText(eventToUpdate.getEVENT_TYPE());
            colorText.setText("#" + Integer.toHexString(eventToUpdate.getEVENT_COLOR()));
            colorText.setBackgroundColor(eventToUpdate.getEVENT_COLOR());
        }

    }

    private void setDateTimeField() {
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
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }

    private void setBeginTimeField() {
        edtTextBegin.setOnClickListener(this);
        Calendar calendar = Calendar.getInstance();
        timePick1 = new TimePickerDialog(rootView.getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Calendar newTime = Calendar.getInstance();
                newTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                newTime.set(Calendar.MINUTE, minute);
                String timeString = DateUtils.formatDateTime(rootView.getContext(), newTime.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME);
                Log.d("Time", hourOfDay + " " + minute);
                edtTextBegin.setText(timeString);
                edtTextBegin.setTag(hourOfDay+":"+minute);
            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), android.text.format.DateFormat.is24HourFormat(rootView.getContext()));
    }


    private void setEndTimeField() {
        edtTextEnd.setOnClickListener(this);
        Calendar calendar = Calendar.getInstance();
        timePick2 = new TimePickerDialog(rootView.getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Calendar newTime = Calendar.getInstance();
                newTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                newTime.set(Calendar.MINUTE, minute);
                String timeString = DateUtils.formatDateTime(rootView.getContext(), newTime.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME);
                edtTextEnd.setText(timeString);
                edtTextEnd.setTag(hourOfDay+":"+minute);
            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), android.text.format.DateFormat.is24HourFormat(rootView.getContext()));
    }

    private void setColorField() {
        colorText.setOnClickListener(this);
        colPicker = new ColorPickerDialog(rootView.getContext(), color);
        colPicker.setAlphaSliderVisible(true);
        colPicker.setHexValueEnabled(true);
        colPicker.setTitle(R.string.choose_color);
        colPicker.setOnColorChangedListener(new ColorPickerDialog.OnColorChangedListener() {
            @Override
            public void onColorChanged(int i) {
                color = i;
                colorText.setText("#" + Integer.toHexString(i));
                colorText.setBackgroundColor(i);
            }
        });
    }

    public void onClick(View view) {
        if (view == edtTextEventDate) {
            eventDatePickerDialog.show();
        } else if (view == edtTextBegin) {
            timePick1.show();
        } else if (view == edtTextEnd) {
            timePick2.show();
        } else if (view == colorText) {
            colPicker.show();
        }
    }

    private void saveEvent() {

        String Start = String.valueOf(edtTextBegin.getTag());
        String End = String.valueOf(edtTextEnd.getTag());
        String Datum = edtTextEventDate.getText().toString();
        String Name = eventName.getText().toString();
        String Type = eventType.getText().toString();
        String Loc = eventPlace.getText().toString();
        String Col = colorText.getText().toString();
        String path ="";
        int c = Color.parseColor(Col);

        if(Start.equals("null"))
            Start = edtTextBegin.getText().toString();

        if(End.equals("null"))
            End = edtTextEnd.getText().toString();

        if(hasRecorded)
            path=mFileName;

        Event e = new Event(Name, Start, End, Datum, Type, Loc, c, path);

        if(eventToUpdate == null){
            dbHandler.addEvent(e);
            int evid = dbHandler.getEventsCount();
            for(int i = 0; i < tasks.size(); i++){
                tasks.get(i).setTASK_EVENTID(evid);
                dbHandler.addTask(tasks.get(i));
            }

            Task.getOpenTasks().clear();
            AlarmHandler.getInstance().addEvent(e);
        }
        else {
            Event upt = new Event(eventID, Name, Start, End, Datum, Type, Loc, c, path);
            dbHandler.updateEvent(upt);
        }

    }


    private void onRecord(boolean start) {

        hasRecorded=true;
        if (start) {
            startRecording();
        } else {
            stopRecording();
        }
    }

    private void onPlay(boolean start) {
        if (start) {
            startPlaying();
        } else {
            stopPlaying();
        }
    }

    private void startPlaying() {
        mPlayer = new MediaPlayer();
        btnPlay.setImageDrawable(getResources().getDrawable(R.drawable.ic_stop_black_24dp));
        try {
            mPlayer.setDataSource(mFileName);
            mPlayer.prepare();
            mPlayer.start();
        } catch (IOException e) {
            Log.e(TAG, "prepare() failed");
        }
    }

    private void stopPlaying() {
        mPlayer.release();
        btnPlay.setImageDrawable(getResources().getDrawable(R.drawable.ic_play_arrow_black_24dp));
        mPlayer = null;
    }

    private void startRecording() {
        btnRecord.setBackgroundColor(Color.RED);
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(mFileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e(TAG, "prepare() failed");
        }

        mRecorder.start();
    }

    private void stopRecording() {
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            btnRecord.setBackground(discardButton.getBackground());
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        if (mRecorder != null) {
            mRecorder.release();
            mRecorder = null;
        }
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }

}

