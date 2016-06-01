package nudelsquad.nudelcalendar;


import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;

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
    TaskBoardAdapter adapter;

    SearchView svtest;

    @Override
    public void onResume() {
        super.onResume();
        adapter.swapItems();
    }


    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Calendar.getInstance().getTime();
        rootView = inflater.inflate(R.layout.activity_taskboard, container, false);
        Bundle args = getArguments();

        taskboardview = (ListView) rootView.findViewById(R.id.taskboard_list);
        final DBHandler dbh = new DBHandler(rootView.getContext());

        final List<Task> tasklist = dbh.getAllTasks();

        adapter = new TaskBoardAdapter(rootView.getContext(), tasklist);

        taskboardview.setAdapter(adapter);
        taskboardview.setClickable(true);


        ArrayList<String> searchlist=new ArrayList<String>();

       for(int i = 0;i<dbh.getEventsCount();i++)
       {
           searchlist.add(dbh.getEvent(i).getEVENT_NAME()+"  "+dbh.getEvent(0).getEVENT_END()+"  "+dbh.getEvent(i).getEVENT_DATUM());
       }
        for (int i = 0; i < dbh.getTasksCount();i++)
        {
            searchlist.add(dbh.getTask(i).getTASK_NAME()+"  "+dbh.getTask(i).getTASK_DATUM());
        }



        svtest = (SearchView) rootView.findViewById(R.id.searchView);
        svtest.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {


                dbh.getAllEvents();

             /*   Context context = getContext();
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, query, duration);
                toast.show();
                */

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                  /*   Context context = getContext();
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, query, duration);
                toast.show();
                */

                return false;
            }
        });
        String a = svtest.getQuery().toString();




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
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.main_frame, new TaskLookView(tasklist.get(position).getTASK_ID()), "NewFragmentTag");
                ft.commit();
            }
        });



        return rootView;


    }
}




