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
public class SettingsViewTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private Solo solo;

    public SettingsViewTest() {
        super(MainActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());
        openNavigationDrawer();



    }


    public void testOpenAndCloseNavigationDrawer() {


        closeNavigationDrawer();


        solo.sleep(2000);

    }

    public void testFindTitle() {
        boolean b;
        b = solo.searchText("NudelCalendar");
        assertEquals(b, true);
        b = solo.searchText("Home");
        assertEquals(b, true);
        b = solo.searchText("Day");
        assertEquals(b, true);
        b = solo.searchText("Week");
        assertEquals(b, true);
//        solo.searchText("NudelCalendar");
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

    public void closeNavigationDrawer() {
        Point deviceSize = new Point();
        getActivity().getWindowManager().getDefaultDisplay().getSize(deviceSize);

        int screenWidth = deviceSize.x;
        int screenHeight = deviceSize.y;

        solo.clickOnScreen(screenWidth-20, screenHeight-20);
    }


}
