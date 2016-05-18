package nudelsquad.nudelcalendar;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class DayList extends Fragment {
    ListView daylist;

    DayListAdapter daylist_adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //setContentView(R.layout.activity_day_list);
        View rootView = inflater.inflate(R.layout.activity_day_list, container, false);
        Bundle args = getArguments();
        daylist = (ListView)rootView.findViewById(R.id.day_eventlist);
        Calendar c = Calendar.getInstance();
        int month = c.get(Calendar.MONTH)+1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        int year = c.get(Calendar.YEAR);
        String currentDate =  day + "-" + ((month > 9) ? month : "0" + month) + "-" + year;

        TextView txDate = (TextView) rootView.findViewById(R.id.txDay);
        txDate.setText(c.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.GERMAN) + " | " + currentDate);

        DBHandler dbh = new DBHandler(rootView.getContext());
        final List<Event> events = dbh.getEventsFromDay(currentDate);

        daylist_adapter = new DayListAdapter(rootView.getContext(), events);
        daylist.setAdapter(daylist_adapter);
        daylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.main_frame, new ShowEventView(events.get(position).getEVENT_ID()), "NewFragmentTag");
                ft.commit();
            }
        });

        TextView txDay = (TextView)rootView.findViewById(R.id.txDay);
        //txDay setText here


        return rootView;
    }
}
