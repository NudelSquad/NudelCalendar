package nudelsquad.nudelcalendar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class TaskCreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_create);


        if(task_datepicker != null)
        {
            task_datepicker.setMinDate(System.currentTimeMillis() - 1000);
        }
    }



    // 2 possibilites
    // First: TaskCreate Window will open in the createEvent Process by clicking the Button "Add Task"
    // Second: TaskCreate Window will open in the taskboard by clicking the "+" symbol



    private TextView tv_taskname;
    private DatePicker task_datepicker;
    private CheckBox chk_setreminder;
    private TextView tv_notes;



    public void cancelClick(View test)
    {
        tv_taskname = (TextView) findViewById(R.id.txt_task);
        tv_taskname.setText("HAHAHAH TEST!!");

    }

    public void doneClick(View test)
    {
       if (tv_taskname.getText()!= null && task_datepicker.setMinDate(); != 0)
       {
            // brings you to the last page this will be the taskwall if you clicked at the "+" at creation.
           // or to the "Create Event" session if you clicked "Add Task" there.

           finish();


       }




    }




}
