package nudelsquad.nudelcalendar.uitest;

import android.graphics.Color;
import android.graphics.Point;
import android.test.ActivityInstrumentationTestCase2;
import android.test.PerformanceTestCase;
import android.util.Log;
import android.view.View;

import com.robotium.solo.Solo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import nudelsquad.nudelcalendar.DBHandler;
import nudelsquad.nudelcalendar.Event;
import nudelsquad.nudelcalendar.MainActivity;
import nudelsquad.nudelcalendar.R;
import nudelsquad.nudelcalendar.Task;

/**
 * Created by emanuel on 20/04/16.
 */
public class WeekViewTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private Solo solo;
    private DBHandler dbHandler;

    public WeekViewTest() {
        super(MainActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());
        dbHandler = new DBHandler(getActivity().getBaseContext());
        dbHandler.resetDatabase();
        setupDB();
        View viewById = getActivity().findViewById(R.id.btn_week);
        solo.clickOnView(viewById);
        solo.sleep(500);


    }

    private void setupDB() {
        Date time = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String today = formatter.format(time);


//        dbHandler.addEvent(new Event("Event 1", "9:00 AM", "12:00 AM", today, "party", "home", Color.GREEN));
//        dbHandler.addEvent(new Event("Event 2", "07:00 AM", "9:00 AM", today, "lecture", "uni", Color.BLUE));

//        public Event(String EVENT_NAME, String EVENT_START, String EVENT_END, String EVENT_DATUM, String EVENT_TYPE,  String EVENT_LOCATION, int EVENT_COLOR)



    }


    public void testWeekGrid() {
        boolean b = solo.searchText("Event 1");
        solo.sleep(1000);
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
        dbHandler.resetDatabase();
        super.tearDown();
    }


}
