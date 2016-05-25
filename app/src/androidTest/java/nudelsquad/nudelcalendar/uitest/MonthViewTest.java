package nudelsquad.nudelcalendar.uitest;

import android.graphics.Color;
import android.graphics.Point;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.TextView;

import com.robotium.solo.Solo;
import com.roomorama.caldroid.CaldroidFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import nudelsquad.nudelcalendar.DBHandler;
import nudelsquad.nudelcalendar.Event;
import nudelsquad.nudelcalendar.MainActivity;
import nudelsquad.nudelcalendar.R;

/**
 * Created by emanuel on 20/04/16.
 */
public class MonthViewTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private Solo solo;
    private DBHandler dbHandler;


    public MonthViewTest() {
        super(MainActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());
        setupDB();
        View viewById = getActivity().findViewById(R.id.btn_month);
        solo.clickOnView(viewById);
        solo.sleep(500);
    }

    private void setupDB() {
        dbHandler = new DBHandler(getActivity().getBaseContext());
        dbHandler.resetDatabase();
        Date time = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String today = formatter.format(time);
        dbHandler.addEvent(new Event("Event 1", "9:00 AM", "12:00 AM", today, "party", "home", Color.GREEN, ""));
        dbHandler.addEvent(new Event("Event 2", "07:00 AM", "9:00 AM", today, "lecture", "uni", Color.BLUE,""));

        time.setDate(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+1);
        today=formatter.format(time);
        dbHandler.addEvent(new Event("Event 3", "07:00 AM", "9:00 AM", today, "lecture", "uni", Color.BLUE,""));
        dbHandler.addEvent(new Event("Event 4", "10:00 AM", "11:00 AM", today, "bla", "uni", Color.BLUE,""));
    }

    public void testFindEventToday(){
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        boolean b;
        b = solo.searchText(String.valueOf(day));
        assertTrue(b);


        solo.clickInList(day, 2);
        solo.sleep(200);

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String today = formatter.format(Calendar.getInstance().getTime());

        b=solo.searchText(today);
        assertTrue(b);

        b=solo.searchText("Event 1");
        assertTrue(b);

        b=solo.searchText("Event 2");
        assertTrue(b);

        solo.sleep(1000);

    }


    public void testClickOnToday() {
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        boolean b;
        b = solo.searchText(String.valueOf(day));
        assertTrue(b);


        solo.clickInList(day, 2);
        solo.sleep(200);

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String today = formatter.format(Calendar.getInstance().getTime());

        b=solo.searchText(today);

        assertTrue(b);
    }

    public void testFindEventTomorrow(){
        Calendar calendar= Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH,1);
        boolean b;
        b = solo.searchText(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
        assertTrue(b);


        solo.clickInList(calendar.get(Calendar.DAY_OF_MONTH), 2);
        solo.sleep(200);

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String today = formatter.format(calendar.getTime());

        b=solo.searchText(today);
        assertTrue(b);

        b=solo.searchText("Event 3");
        assertTrue(b);

        b=solo.searchText("Event 4");
        assertTrue(b);

        solo.sleep(1000);

    }


    public void openNavigationDrawer() {
        Point deviceSize = new Point();
        getActivity().getWindowManager().getDefaultDisplay().getSize(deviceSize);

        int screenWidth = deviceSize.x;
        int screenHeight = deviceSize.y;
        int fromX = 0;
        int toX = screenWidth / 2;
        int fromY = screenHeight / 2;
        int toY = fromY;

        solo.drag(fromX, toX, fromY, toY, 1);
    }


}
