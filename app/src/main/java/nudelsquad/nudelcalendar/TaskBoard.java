package nudelsquad.nudelcalendar;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by waser2 on 27.04.2016.
 */
public class TaskBoard extends Fragment{
ListView taskboardview;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.activity_taskboard,container,false);
        Bundle args = getArguments();
        taskboardview = (ListView)rootView.findViewById(R.id.taskboard_list);
        final List<Task> tasklist = new ArrayList<Task>();
        tasklist.add(new Task("Task 12", 200, "ho"));
        tasklist.add(new Task("Task Müll wegraumen",50, "Notes bitte nicht vergessen..."));
        tasklist.add(new Task("Task Müll wegraumen",60, "Notes bitte nicht vergessen..."));
        tasklist.add(new Task("Task Müll wegraumen",90, "NoteBLABLABLBABALBALBALBen..."));

      //  String[] test = new String[]{"23","44","55"};


        TaskBoardAdapter adapter = new TaskBoardAdapter(rootView.getContext(), tasklist);

        final ListView listView = (ListView) rootView.findViewById(R.id.taskboard_list);
        listView.setAdapter(adapter);
        listView.setClickable(true);

        TextView txTaskHeader = (TextView)rootView.findViewById(R.id.txTasks);

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


        return rootView;




    }
}
