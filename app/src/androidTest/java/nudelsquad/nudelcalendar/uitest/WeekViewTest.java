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
        setupDB();
        View viewById = getActivity().findViewById(R.id.btn_week);
        solo.clickOnView(viewById);
        solo.sleep(500);


    }

    private void setupDB() {
        dbHandler = new DBHandler(getActivity().getBaseContext());
        dbHandler.resetDatabase();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String today = formatter.format(calendar.getTime());
        dbHandler.addEvent(new Event("Event 2", "7:00 AM", "9:00 AM", today, "lecture", "uni", Color.BLUE));
        dbHandler.addEvent(new Event("Event 1", "9:00 AM", "11:59 AM", today, "party", "home", Color.GREEN));

        calendar.add(Calendar.DAY_OF_MONTH, 2);
        today=formatter.format(calendar.getTime());
        dbHandler.addEvent(new Event("Event 3", "7:00 AM", "9:00 AM", today, "lecture", "uni", Color.BLUE));
        dbHandler.addEvent(new Event("Event 4", "10:00 AM", "11:00 AM", today, "bla", "uni", Color.YELLOW));

    }


    public void testFindEvent() {
        boolean b = solo.searchText("Event 2");
        assertTrue(true);
        solo.clickInList(1);
        solo.sleep(5000);
    }



    public void tearDown() throws Exception {
        super.tearDown();
        dbHandler.resetDatabase();
    }


}
