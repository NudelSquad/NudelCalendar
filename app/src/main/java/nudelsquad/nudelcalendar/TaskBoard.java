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

        search_list.start(rootView,this.getContext(),dbh);

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
/*
    private void setupSearchView()
    {
        searchview.setIconifiedByDefault(false);
        searchview.setOnQueryTextListener(this);
        searchview.setSubmitButtonEnabled(true);
        searchview.setQueryHint("Search Here");
    }

    @Override
    public boolean onQueryTextChange(String newText)
    {
        if (TextUtils.isEmpty(newText)) {
            lv_search.clearTextFilter();
        } else {
            lv_search.setFilterText(newText);
        }
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query)
    {
        return false;
    }


    public void fillSearchItemList(List lst)
    {

        if(lst.get(0).getClass() == Event.class)
        {
            for(int index = 0; index < lst.size();index++)
            {
                Event temp_event = (Event) lst.get(index);
                int temp_id = temp_event.getEVENT_ID();
                String temp_name = temp_event.getEVENT_NAME();
                String temp_date = temp_event.getEVENT_DATUM();
                Boolean temp_isTask = false;

               // SearchItem temp = new SearchItem();
                searchitem_list.add(new SearchItem(temp_id,temp_name,temp_date,temp_isTask));
            }
        }
        else
        {
            for(int index = 0; index < lst.size();index++)
            {
                Task temp_task = (Task) lst.get(index);
                int temp_id = temp_task.getTASK_ID();
                String temp_name = temp_task.getTASK_NAME();
                String temp_date = temp_task.getTASK_DATUM();
                Boolean temp_isTask = true;

                searchitem_list.add(new SearchItem(temp_id,temp_name,temp_date,temp_isTask));
            }
        }

    }
    */
}




