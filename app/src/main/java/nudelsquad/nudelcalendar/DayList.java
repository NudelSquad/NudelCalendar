package nudelsquad.nudelcalendar;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class DayList extends Fragment {
    ListView daylist;
    DayListAdapter daylist_adapter;


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
        eventlist.add(new EventBean("8:00","13:00","test_event","meeting","graz", Color.BLUE,1));
        eventlist.add(new EventBean("09:00","12:00","test_event","meeting","graz", Color.RED,2));
        eventlist.add(new EventBean("8:00","13:00","test_event","meeting","graz", Color.BLUE,3));
        eventlist.add(new EventBean("09:00","12:00","test_event","meeting","graz", Color.RED,4));
        eventlist.add(new EventBean("8:00","13:00","test_event","meeting","graz", Color.BLUE,5));
        eventlist.add(new EventBean("09:00","12:00","test_event","meeting","graz", Color.RED,6));
        eventlist.add(new EventBean("8:00","13:00","test_event","meeting","graz", Color.BLUE,7));
        eventlist.add(new EventBean("09:00","12:00","test_event","meeting","graz", Color.RED,8));
        eventlist.add(new EventBean("8:00","13:00","test_event","meeting","graz", Color.BLUE,9));
        eventlist.add(new EventBean("09:00","12:00","test_event","meeting","graz", Color.RED,10));
        eventlist.add(new EventBean("8:00","13:00","test_event","meeting","graz", Color.BLUE,11));
        daylist_adapter = new DayListAdapter(rootView.getContext(), eventlist);
        daylist.setAdapter(daylist_adapter);
        daylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.main_frame, new ShowEventView(), "NewFragmentTag");
                ft.commit();
            }
        });

        TextView txDay = (TextView)rootView.findViewById(R.id.txDay);
        //txDay setText here


        return rootView;
    }
}
