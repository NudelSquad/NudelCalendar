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
import java.util.Calendar;
import java.util.List;

import java.util.Objects;
import java.util.zip.Inflater;


/**
 * Created by waser2 on 27.04.2016.
 */
public class TaskBoard extends Fragment {
    ListView taskboardview;
    private View rootView;


    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Calendar.getInstance().getTime();
        rootView = inflater.inflate(R.layout.activity_taskboard, container, false);
        Bundle args = getArguments();

        taskboardview = (ListView) rootView.findViewById(R.id.taskboard_list);
        DBHandler dbh = new DBHandler(rootView.getContext());

        List<Task> tasklist = dbh.getAllTasks();

        TaskBoardAdapter adapter = new TaskBoardAdapter(rootView.getContext(), tasklist);

        taskboardview.setAdapter(adapter);
        taskboardview.setClickable(true);


        taskboardview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Object item = taskboardview.getAdapter().getItem(position);
                TextView description = (TextView) view.findViewById(R.id.txt_description);


                int visibility = description.getVisibility();
                if (visibility == View.GONE)
                    description.setVisibility(View.VISIBLE);
                else
                    description.setVisibility(View.GONE);

                return true;
            }
        });

        taskboardview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "Link click", Toast.LENGTH_LONG).show();
            }
        });

        return rootView;


    }
}




