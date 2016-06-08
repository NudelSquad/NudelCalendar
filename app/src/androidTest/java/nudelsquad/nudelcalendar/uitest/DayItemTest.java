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
public class DayItemTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private Solo solo;
    private DBHandler dbHandler;

    public DayItemTest() {
        super(MainActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        setupDB();
        solo = new Solo(getInstrumentation(), getActivity());
        View viewById = getActivity().findViewById(R.id.btn_day);
        solo.clickOnView(viewById);
        solo.clickInList(1);
        solo.sleep(100);

    }

    private void setupDB() {
        dbHandler = new DBHandler(getActivity().getBaseContext());
        dbHandler.resetDatabase();
        Date time = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String today = formatter.format(time);
        dbHandler.addEvent(new Event("Event 1", "9:00", "12:00", today, "party", "home", Color.GREEN,""));
    }

    public void testClickInList() {
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

    public void testDeleteButton(){
        solo.clickOnButton(getActivity().getResources().getString(R.string.delete));
        solo.sleep(100);
        solo.searchText(getActivity().getResources().getString(R.string.event_delete));
        solo.sleep(100);
        openNavigationDrawer();
        solo.clickOnText(getActivity().getResources().getString(R.string.drawer_day));

        boolean b = solo.searchText("Event 1");
        assertFalse(b);

    }


    public void testDeleteEdit(){
        solo.clickOnButton(getActivity().getResources().getString(R.string.delete));

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
        super.tearDown();
    }

}
