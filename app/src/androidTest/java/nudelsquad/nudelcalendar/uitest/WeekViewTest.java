package nudelsquad.nudelcalendar.uitest;

import android.graphics.Point;
import android.test.ActivityInstrumentationTestCase2;
import android.test.PerformanceTestCase;
import android.view.View;

import com.robotium.solo.Solo;

import java.util.Calendar;

import nudelsquad.nudelcalendar.MainActivity;
import nudelsquad.nudelcalendar.R;

/**
 * Created by emanuel on 20/04/16.
 */
public class WeekViewTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private Solo solo;

    public WeekViewTest() {
        super(MainActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());
        openNavigationDrawer();
        solo.clickOnText("Week");
        solo.sleep(500);

    }


    public void testWeekGrid(){
        int i = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        boolean b;
        b = solo.searchText(Integer.toString(i));
        assertTrue(b);
        b = solo.searchText(Integer.toString(++i));
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
        super.tearDown();
    }





}
