package nudelsquad.nudelcalendar;

import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class DayList extends Fragment {
    ListView daylist;

    DayListAdapter daylist_adapter;



           @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            //setContentView(R.layout.activity_day_list);
               ((AppCompatActivity) getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getActivity().getResources().getColor(R.color.colorYellow)));
               Window window = getActivity().getWindow();
               window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
               window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
               if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                   window.setStatusBarColor(getActivity().getResources().getColor(R.color.colorDarkYellow));
               }
               View rootView = inflater.inflate(R.layout.activity_day_list, container, false);
            Bundle args = getArguments();
            daylist = (ListView)rootView.findViewById(R.id.day_eventlist);

        String selectedDate = MainActivity.myBundle.getString("selectedDate");
        String not = "n";
        String currentDate;
        String DayOfWeek;
        Calendar c = Calendar.getInstance();

        if(selectedDate.equals(not)) {
            int month = c.get(Calendar.MONTH)+1;
            int day = c.get(Calendar.DAY_OF_MONTH);
            int year = c.get(Calendar.YEAR);
            currentDate =  ((day > 9) ? day : "0" + day)  + "-" + ((month > 9) ? month : "0" + month) + "-" + year;
            DayOfWeek = c.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.GERMAN);
        }
        else {
            MainActivity.myBundle.putString("selectedDate", String.valueOf(not));
            currentDate = selectedDate;
            String dateString = selectedDate;
            Date date = null;
            try {
                date = new SimpleDateFormat("dd-MM-yyyy").parse(dateString);
            } catch (ParseException e) {
                Log.d("Fail: ", "Da failt was!");
            }

            DayOfWeek = new SimpleDateFormat("EEEE", Locale.GERMANY).format(date);

        }




        TextView txDate = (TextView) rootView.findViewById(R.id.txDay);
        txDate.setText(DayOfWeek + " | " + currentDate);

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
