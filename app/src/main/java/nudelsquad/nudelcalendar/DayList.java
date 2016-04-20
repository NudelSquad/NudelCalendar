package nudelsquad.nudelcalendar;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class DayList extends Fragment {
    ListView daylist;
    DayListAdapter daylist_adapter;
    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_list);
        daylist = (ListView)findViewById(R.id.day_eventlist);

        List<EventBean> eventlist = new ArrayList<EventBean>();
        eventlist.add(new EventBean("09:00","12:00","test_event","meeting","graz", Color.RED,0));
        eventlist.add(new EventBean("8:00","13:00","test_event","meeting","graz", Color.BLUE,0));
        daylist_adapter = new DayListAdapter(this, eventlist);
        daylist.setAdapter(daylist_adapter);
    }
    */

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //setContentView(R.layout.activity_day_list);
        View rootView = inflater.inflate(
                R.layout.activity_day_list, container, false);
        Bundle args = getArguments();
        daylist = (ListView)rootView.findViewById(R.id.day_eventlist);
        List<EventBean> eventlist = new ArrayList<EventBean>();
        eventlist.add(new EventBean("09:00","12:00","test_event","meeting","graz", Color.RED,0));
        eventlist.add(new EventBean("8:00","13:00","test_event","meeting","graz", Color.BLUE,0));
        daylist_adapter = new DayListAdapter(rootView.getContext(), eventlist);
        daylist.setAdapter(daylist_adapter);

        return rootView;
    }
}
