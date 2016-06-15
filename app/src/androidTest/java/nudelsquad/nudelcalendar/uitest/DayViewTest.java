package nudelsquad.nudelcalendar.uitest;

import android.graphics.Color;
import android.graphics.Point;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;

import com.robotium.solo.Solo;

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
public class DayViewTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private Solo solo;
    private DBHandler dbHandler;

    public DayViewTest() {
        super(MainActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());
        setupDB();
        View viewById = getActivity().findViewById(R.id.btn_day);
        solo.sleep(100);
        solo.clickOnView(viewById);
        solo.sleep(500);

    }

    private void setupDB() {
        dbHandler = new DBHandler(getActivity().getBaseContext());
        dbHandler.resetDatabase();
        Date time = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String today = formatter.format(time);
        dbHandler.addEvent(new Event("Event 1", "9:00", "12:00", today, "party", "home", Color.GREEN,""));
        dbHandler.addEvent(new Event("Event 2", "07:00", "9:00", today, "lecture", "uni", Color.BLUE,""));
    }

    public void testCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String today = formatter.format(calendar.getTime());
        boolean b = solo.searchText(today);
        assertTrue(b);

    }


    public void testFindOneEvent() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        boolean b;
        b = solo.searchText("Event 1");
        assertTrue(b);
        b = solo.searchText("9:00");
        assertTrue(b);
        b = solo.searchText("12:00");
        assertTrue(b);
        b = solo.searchText("party");
        assertTrue(b);
        b = solo.searchText("home");
        assertTrue(b);


    }


    public void testFindMultipleEvents() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String today = formatter.format(calendar.getTime());
        boolean b;


        b = solo.searchText(today);
        assertTrue(b);
        b = solo.searchText("Event 1");
        assertTrue(b);
        b = solo.searchText("9:00");
        assertTrue(b);
        b = solo.searchText("12:00");
        assertTrue(b);
        b = solo.searchText("party");
        assertTrue(b);
        b = solo.searchText("home");
        assertTrue(b);


        b = solo.searchText("Event 2");
        assertTrue(b);
        b = solo.searchText("7:00");
        assertTrue(b);
        b = solo.searchText("9:00");
        assertTrue(b);
        b = solo.searchText("lecture");
        assertTrue(b);
        b = solo.searchText("uni");
        assertTrue(b);
    }


    public void testClickInList() {
        solo.clickInList(1);
        solo.sleep(100);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String today = formatter.format(calendar.getTime());
        boolean b;


        b = solo.searchText(today);
        assertTrue(b);
        b = solo.searchText("Event 1");
        assertTrue(b);
        b = solo.searchText("9:00");
        assertTrue(b);
        b = solo.searchText("12:00");
        assertTrue(b);
        b = solo.searchText("party");
        assertTrue(b);
        b = solo.searchText("home");
        assertTrue(b);
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

    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
    }

}
