package nudelsquad.nudelcalendar;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v4.app.Fragment;

import java.util.Calendar;
import java.util.List;

import nudelsquad.nudelcalendar.search.SearchList;


/**
 * Created by waser2 on 27.04.2016.
 */
public class TaskBoard extends Fragment{
    private ListView taskboardview;
    private View rootView;
    private TaskBoardAdapter adapter;

    SearchList search_list = new SearchList();

    @Override
    public void onResume() {
        super.onResume();
        adapter.swapItems();
    }


    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        Calendar.getInstance().getTime();
        rootView = inflater.inflate(R.layout.activity_taskboard, container, false);
        Bundle args = getArguments();

        taskboardview = (ListView) rootView.findViewById(R.id.taskboard_list);
        final DBHandler dbh = new DBHandler(rootView.getContext());
        final List<Task> tasklist = dbh.getAllTasks();

        adapter = new TaskBoardAdapter(rootView.getContext(), tasklist);
        taskboardview.setAdapter(adapter);
        taskboardview.setClickable(true);


        taskboardview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Object item = taskboardview.getAdapter().getItem(position);
                TextView description = (TextView) view.findViewById(R.id.txt_description);

                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.main_frame, new TaskLookView(tasklist.get(position).getTASK_ID()), "NewFragmentTag");
                ft.commit();

                return true;
            }
        });

        taskboardview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView description = (TextView) view.findViewById(R.id.txt_description);


                int visibility = description.getVisibility();
                if (visibility == View.GONE)
                    description.setVisibility(View.VISIBLE);
                else
                    description.setVisibility(View.GONE);

            }
        });

        return rootView;
    }
}




