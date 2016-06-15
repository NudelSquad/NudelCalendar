package nudelsquad.nudelcalendar;

import android.graphics.Color;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alamkanak.weekview.*;
import com.alamkanak.weekview.WeekView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Created by Marco on 27.04.2016.
 */
public class WeekViewBase extends Fragment implements WeekView.EventClickListener, MonthLoader.MonthChangeListener, WeekView.EventLongPressListener, WeekView.EmptyViewLongPressListener {
        private static final int TYPE_DAY_VIEW = 1;
        private static final int TYPE_THREE_DAY_VIEW = 2;
        private static final int TYPE_WEEK_VIEW = 3;
        private int mWeekViewType = TYPE_THREE_DAY_VIEW;
        private com.alamkanak.weekview.WeekView mWeekView;
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.week_fragment, container, false);
        // Get a reference for the week view in the layout.
        mWeekView = (com.alamkanak.weekview.WeekView) rootView.findViewById(R.id.weekView);

        // Show a toast message about the touched event.
        mWeekView.setOnEventClickListener(this);

        // The week view has infinite scrolling horizontally. We have to provide the events of a
        // month every time the month changes on the week view.
        mWeekView.setMonthChangeListener(this);

        // Set long press listener for events.
        mWeekView.setEventLongPressListener(this);

        // Set long press listener for empty view
        mWeekView.setEmptyViewLongPressListener(this);

        // Set up a date time interpreter to interpret how the date and time will be formatted in
        // the week view. This is optional



        setupDateTimeInterpreter(false);
        mWeekView.notifyDatasetChanged();

        /*
        if (mWeekViewType != TYPE_WEEK_VIEW) {
            mWeekViewType = TYPE_WEEK_VIEW;
            mWeekView.setNumberOfVisibleDays(7);

            // Lets change some dimensions to best fit the view.
            mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics()));
            mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics()));
            mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics()));
        }
        */
        return rootView;
    }


            /**
             * Set up a date time interpreter which will show short date values when in week view and long
             * date values otherwise.
             * @param shortDate True if the date values should be short.
             */
        private void setupDateTimeInterpreter(final boolean shortDate) {
            mWeekView.setDateTimeInterpreter(new DateTimeInterpreter() {
                @Override
                public String interpretDate(Calendar date) {
                    //SimpleDateFormat weekdayNameFormat = new SimpleDateFormat("EEE", Locale.getDefault());
                    //String weekday = weekdayNameFormat.format(date.getTime());
                    SimpleDateFormat format = new SimpleDateFormat("dd.MM", Locale.getDefault());

                    // All android api level do not have a standard way of getting the first letter of
                    // the week day name. Hence we get the first char programmatically.
                    // Details: http://stackoverflow.com/questions/16959502/get-one-letter-abbreviation-of-week-day-of-a-date-in-java#answer-16959657
                    //if (shortDate)
                      //  weekday = String.valueOf(weekday.charAt(0));

                    //return weekday.toUpperCase() + format.format(date.getTime());
                    //Log.e("Date", "" + format.format((date.getTime())));
                    return format.format(date.getTime());
                }

                @Override
                public String interpretTime(int hour) {
                    if (hour < 10)
                        return "0" + hour + ":00";
                    return hour + ":00";
                }
            });
        }

        @Override
        public void onEventClick(WeekViewEvent event, RectF eventRect) {                //go to Event

            final FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.main_frame, new ShowEventView((int) event.getId()), "NewFragmentTag");
            ft.commit();
        }

        @Override
        public void onEventLongPress(WeekViewEvent event, RectF eventRect) {
            Toast.makeText(rootView.getContext(), "Event", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onEmptyViewLongPress(Calendar time) {
            Toast.makeText(rootView.getContext(), "Empty", Toast.LENGTH_LONG).show();
        }

    @Override
    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {
        List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();

        DBHandler dbh = new DBHandler(rootView.getContext());

        List<Event> items= dbh.getEventsFromMonthOfYear(newMonth + "-" + newYear);
        for (Event e:items) {
            Calendar startTime = Calendar.getInstance();
            DateFormat timeFormat = new SimpleDateFormat("hh:mm");

            Date start = null;
            Date end=null;
            try {
                start = timeFormat.parse(e.getEVENT_START());
                end = timeFormat.parse(e.getEVENT_END());
            } catch (ParseException e1) {
                e1.printStackTrace();
            }

            String[] dat = e.getEVENT_DATUM().split("-");
            startTime.set(Calendar.HOUR_OF_DAY, start.getHours());
            startTime.set(Calendar.MINUTE, start.getMinutes());
            startTime.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dat[0]));
            startTime.set(Calendar.MONTH, newMonth - 1);
            startTime.set(Calendar.YEAR, newYear);



            long milliseconds = getDateDiff(start, end, TimeUnit.MILLISECONDS);
            int minutes = (int) ((milliseconds / (1000*60)) % 60);
            int hours   = (int) ((milliseconds / (1000*60*60)) % 24);



            Calendar endTime = (Calendar) startTime.clone();
            endTime.add(Calendar.HOUR, hours);
            endTime.add(Calendar.MINUTE, minutes);
            endTime.set(Calendar.MONTH, newMonth - 1);
            endTime.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dat[0]));
            WeekViewEvent event = new WeekViewEvent(e.getEVENT_ID(), e.getEVENT_NAME(), startTime, endTime);
            event.setColor(e.getEVENT_COLOR());
            events.add(event);
        }

        return events;
    }

    /**
     * Get a diff between two dates
     * @param date1 the oldest date
     * @param date2 the newest date
     * @param timeUnit the unit in which you want the diff
     * @return the diff value, in the provided unit
     */
    public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

}
