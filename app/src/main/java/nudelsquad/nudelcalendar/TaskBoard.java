package nudelsquad.nudelcalendar;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
<<<<<<< HEAD
import java.util.Objects;
=======
import java.util.zip.Inflater;
>>>>>>> createTask

/**
 * Created by waser2 on 27.04.2016.
 */
public class TaskBoard extends Fragment {
    ListView taskboardview;


    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.activity_taskboard, container, false);
        Bundle args = getArguments();
<<<<<<< HEAD
        taskboardview = (ListView)rootView.findViewById(R.id.taskboard_list);
        final List<Task> tasklist = new ArrayList<Task>();
=======
        taskboardview = (ListView) rootView.findViewById(R.id.taskboard_list);
        List<Task> tasklist = new ArrayList<Task>();
>>>>>>> createTask
        tasklist.add(new Task("Task 12", 200, "ho"));
        tasklist.add(new Task("Task Müll wegraumen", 50, "Notes bitte nicht vergessen..."));
        tasklist.add(new Task("Task Müll wegraumen", 60, "Notes bitte nicht vergessen..."));
        tasklist.add(new Task("Task Müll wegraumen", 90, "NoteBLABLABLBABALBALBALBen..."));
        tasklist.add(new Task("Task Müll wegraumen", 50, "Notes bitte nicht vergessen..."));
        tasklist.add(new Task("Task Müll wegraumen", 60, "Notes bitte nicht vergessen..."));
        tasklist.add(new Task("Task Müll wegraumen", 90, "NoteBLABLABLBABALBALBALBen..."));
        tasklist.add(new Task("Task Müll wegraumen", 50, "Notes bitte nicht vergessen..."));
        tasklist.add(new Task("Task Müll wegraumen", 60, "Notes bitte nicht vergessen..."));
        tasklist.add(new Task("Task Müll wegraumen", 90, "NoteBLABLABLBABALBALBALBen..."));
        tasklist.add(new Task("Task Müll wegraumen", 50, "Notes bitte nicht vergessen..."));
        tasklist.add(new Task("Task Müll wegraumen", 60, "Notes bitte nicht vergessen..."));
        tasklist.add(new Task("Task Müll wegraumen", 90, "NoteBLABLABLBABALBALBALBen..."));

        //  String[] test = new String[]{"23","44","55"};


        TaskBoardAdapter adapter = new TaskBoardAdapter(rootView.getContext(), tasklist);

        final ListView listView = (ListView) rootView.findViewById(R.id.taskboard_list);
        listView.setAdapter(adapter);
        listView.setClickable(true);


<<<<<<< HEAD
        /*
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

           // public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                //view = this.inflater.inflate(R.layout.task_item,parent,false);
           //     String message = "abc";
            //    intent.putExtra(EXTRA_MESSAGE, message);
             //   startActivity(intent)

       //    }


           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // Objects listItem = taskboardview.getItemAtPosition(position);

                TextView test = (TextView) rootView.findViewById(R.id.tv_expand);
            test.setText("DASSS IST DIE NOTE");
            }
        });
*/
=======
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Object item = listView.getAdapter().getItem(position);
                TextView description = (TextView) view.findViewById(R.id.txt_descpriton);
>>>>>>> createTask

                int visibility = description.getVisibility();
                if (visibility == View.GONE)
                    description.setVisibility(View.VISIBLE);
                else
                    description.setVisibility(View.GONE);

                return true;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "Link click", Toast.LENGTH_LONG).show();
            }
        });

        return rootView;


    }
}




