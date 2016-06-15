package nudelsquad.nudelcalendar.uitest;

import android.graphics.Color;
import android.graphics.Point;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;

import com.robotium.solo.Solo;

import java.util.List;

import nudelsquad.nudelcalendar.DBHandler;
import nudelsquad.nudelcalendar.Event;
import nudelsquad.nudelcalendar.MainActivity;
import nudelsquad.nudelcalendar.R;
import nudelsquad.nudelcalendar.Task;

/**
 * Created by emanuel on 20/04/16.
 */
public class SettingsViewTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private Solo solo;
    private DBHandler dbh;

    public SettingsViewTest() {
        super(MainActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());


    }

    public void testSwitcher() {

        openNavigationDrawer();
        solo.clickOnText(getActivity().getResources().getString(R.string.drawer_settings));
        solo.sleep(1000);
        View swLS = getActivity().findViewById(R.id.swLS);
        View swRem = getActivity().findViewById(R.id.swRem);
        solo.clickOnView(swLS);
        solo.clickOnView(swRem);
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
        solo.sleep(500);
    }

    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
    }



}
