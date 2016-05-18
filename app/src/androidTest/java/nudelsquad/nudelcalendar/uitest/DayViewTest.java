package nudelsquad.nudelcalendar.uitest;

import android.graphics.Point;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;

import com.robotium.solo.Solo;

import nudelsquad.nudelcalendar.MainActivity;
import nudelsquad.nudelcalendar.R;

/**
 * Created by emanuel on 20/04/16.
 */
public class DayViewTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private Solo solo;

    public DayViewTest() {
        super(MainActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());
        openNavigationDrawer();
        solo.clickOnText("Day");
        solo.sleep(500);

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
