package nudelsquad.nudelcalendar;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.Toast;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * Created by Sathearo on 27.04.2016.
 */
public class MonthView extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getActivity().getResources().getColor(R.color.colorBrown)));
        Window window = getActivity().getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getActivity().getResources().getColor(R.color.colorDarkBrown));
        }

        final View rootView = inflater.inflate(R.layout.month_fragment, container, false);

        final CaldroidFragment caldroidFragment = new CaldroidFragment();
        Bundle args = new Bundle();
        final Calendar cal = Calendar.getInstance();
        args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
        args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
        caldroidFragment.setArguments(args);


        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.calendarFragment, caldroidFragment, "NewFragmentTag");
        ft.commit();

        final CaldroidListener listener = new CaldroidListener() {

            @Override
            public void onSelectDate(Date date, View view) {
                String month = (String) android.text.format.DateFormat.format("MM", date);
                String year = (String) android.text.format.DateFormat.format("yyyy", date);
                String day = (String) android.text.format.DateFormat.format("dd", date);
                String selectedDate = day + "-" + month + "-" + year;
                MainActivity.myBundle.putString("selectedDate", String.valueOf(selectedDate));

                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.main_frame, new DayList(), "NewFragmentTag");
                ft.commit();
            }

            @Override
            public void onChangeMonth(int month, int year) {
                String monthOfYear = ((month > 9) ? month : "0" + month) + "-" + year;

                DBHandler dbh = new DBHandler(rootView.getContext());
                final List<Event> events = dbh.getEventsFromMonthOfYear(monthOfYear);

                String dayWithEventString;
                Date dayWithEvent;

                for (Event e: events) {
                    dayWithEventString = e.getEVENT_DATUM();

                    DateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.GERMANY);
                    ParsePosition pos = new ParsePosition(0);
                    dayWithEvent = format.parse(dayWithEventString, pos);

                    Calendar c1 = GregorianCalendar.getInstance();
                    c1.setTime(dayWithEvent);
                    Date greenDate = c1.getTime();
                    ColorDrawable green = new ColorDrawable(Color.GREEN);

                    caldroidFragment.setBackgroundDrawableForDate(green, greenDate);
                }
                caldroidFragment.refreshView();
            }

            @Override
            public void onLongClickDate(Date date, View view) {
                //Toast.makeText(rootView.getContext(),
                //      "Long click " + "Date = " + date,
                //      Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCaldroidViewCreated() {
                //Toast.makeText(rootView.getContext(),
                //"Caldroid view is created",
                //Toast.LENGTH_SHORT).show();
            }
        };

        caldroidFragment.setCaldroidListener(listener);

        return rootView;
    }
}
