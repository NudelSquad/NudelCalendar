package nudelsquad.nudelcalendar;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

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
        taskboardview = (ListView)rootView.findViewById(R.id.taskboard_events);
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
       // daylist_adapter = new DayListAdapter(rootView.getContext(), eventlist);
        //daylist.setAdapter(daylist_adapter);

        TextView txDay = (TextView)rootView.findViewById(R.id.txEvents);
        //txDay setText here


        return rootView;



       // return super.onCreateView(inflater, container, savedInstanceState);
    }
}
