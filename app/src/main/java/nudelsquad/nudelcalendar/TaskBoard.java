package nudelsquad.nudelcalendar;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

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
        List<Task> tasklist = new ArrayList<Task>();
        tasklist.add(new Task("Task 12", 200, "ho"));
        tasklist.add(new Task("Task Müll wegraumen",50, "Notes bitte nicht vergessen..."));

       // daylist_adapter = new DayListAdapter(rootView.getContext(), eventlist);
        //daylist.setAdapter(daylist_adapter);

        TextView txTaskHeader = (TextView)rootView.findViewById(R.id.txTasks);



        return rootView;




    }
}
