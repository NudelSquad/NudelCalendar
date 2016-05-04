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

import java.util.Objects;
import java.util.zip.Inflater;


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

        taskboardview = (ListView) rootView.findViewById(R.id.taskboard_list);
        List<Task> tasklist = new ArrayList<Task>();

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
        

        TaskBoardAdapter adapter = new TaskBoardAdapter(rootView.getContext(), tasklist);

        final ListView listView = (ListView) rootView.findViewById(R.id.taskboard_list);
        listView.setAdapter(adapter);
        listView.setClickable(true);


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Object item = listView.getAdapter().getItem(position);
                TextView description = (TextView) view.findViewById(R.id.txt_descpriton);


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




