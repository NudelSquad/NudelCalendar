package nudelsquad.nudelcalendar.search;

import android.app.Fragment;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

import nudelsquad.nudelcalendar.DBHandler;
import nudelsquad.nudelcalendar.Event;
import nudelsquad.nudelcalendar.R;
import nudelsquad.nudelcalendar.Task;

public class SearchList extends Fragment implements SearchView.OnQueryTextListener{


    private SearchView searchview;
    private ListView lv_search;
    private ArrayList<SearchItem> searchitem_list;
    private SearchListAdapter searchlist_adapter;


    public void start(View rootView, Context context, DBHandler dbh)
    {
        searchview = (SearchView) rootView.findViewById(R.id.searchView);
        lv_search = (ListView) rootView.findViewById(R.id.listViewSearch);

        List<Event> list_events = dbh.getAllEvents();
        List<Task> list_tasks = dbh.getAllTasks();

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
            searchlist_adapter = new SearchListAdapter(context, searchitem_list);
            lv_search.setAdapter(searchlist_adapter);
            lv_search.setTextFilterEnabled(true);
            setupSearchView();
        }
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


}
