package nudelsquad.nudelcalendar.search;

import android.os.TransactionTooLargeException;
import android.support.v4.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.ListView;
import android.widget.SearchView;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import nudelsquad.nudelcalendar.DBHandler;
import nudelsquad.nudelcalendar.Event;
import nudelsquad.nudelcalendar.R;
import nudelsquad.nudelcalendar.ShowEventView;
import nudelsquad.nudelcalendar.Task;
import nudelsquad.nudelcalendar.TaskLookView;

public class SearchList extends Fragment implements SearchView.OnQueryTextListener{


    private SearchView searchview;
    private ListView lv_search;
    private ArrayList<SearchItem> searchitem_list;
    private SearchListAdapter searchlist_adapter;
    private View rootView;
    private DBHandler dbh;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.search_fragment, container, false);
        searchview = (SearchView) rootView.findViewById(R.id.searchView);
        lv_search = (ListView) rootView.findViewById(R.id.listViewSearch);

        dbh = new DBHandler(rootView.getContext());

        final List<Event> list_events = dbh.getAllEvents();
        final List<Task> list_tasks = dbh.getAllTasks();

        searchitem_list = new ArrayList<SearchItem>();

        if(!list_events.isEmpty())
        {
            fillSearchItemList(dbh.getAllEvents());
        }

        if(!list_tasks.isEmpty())
        {
            fillSearchItemList(dbh.getAllTasks());
        }

        if(!searchitem_list.isEmpty())
        {
            searchlist_adapter = new SearchListAdapter(getContext(), searchitem_list);
            lv_search.setAdapter(searchlist_adapter);
            lv_search.setTextFilterEnabled(true);
            setupSearchView();
        }

        lv_search.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                Fragment fragment;
                FragmentManager fm;
                FragmentTransaction transaction;


               SearchItem temp = (SearchItem) adapterView.getAdapter().getItem(i);

                if(temp.getIs_a_task_())
                {
                    for(Task task : list_tasks)
                    {
                        if(task.getTASK_ID() == temp.getId_())
                        {
                            // Its an task
                            fragment= new TaskLookView(temp.getId_());
                            fm = getFragmentManager();
                            transaction = fm.beginTransaction();
                            transaction.replace(R.id.main_frame, fragment);
                            transaction.commit();
                        }
                    }
                }
                else
                {
                    for(Event ev : list_events)
                    {
                        if(ev.getEVENT_ID() == temp.getId_())
                        {
                            // its an event
                            fragment= new ShowEventView(temp.getId_());
                            fm = getFragmentManager();
                            transaction = fm.beginTransaction();
                            transaction.replace(R.id.main_frame, fragment);
                            transaction.commit();
                        }
                    }
                }

                return false;
            }
        });

        return rootView;
    }

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
        Filter filter = searchlist_adapter.getFilter();
        if (TextUtils.isEmpty(newText)) {
            filter.filter("");
        } else {
            filter.filter(newText);
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
}
