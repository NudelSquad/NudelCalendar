package nudelsquad.nudelcalendar.uitest;

import android.graphics.Point;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;

import com.robotium.solo.Solo;

import nudelsquad.nudelcalendar.MainActivity;
import nudelsquad.nudelcalendar.R;

public class DeleteAllEventsTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private Solo solo;

    public DeleteAllEventsTest() {
        super(MainActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());
        openNavigationDrawer();
        solo.clickOnText(getActivity().getResources().getString(R.string.drawer_settings));
        solo.sleep(1000);
    }

    public void testDeleteAllEvents() {
        View v = getActivity().findViewById(R.id.btnDelEvents);
        solo.clickOnView(v);
        solo.sleep(500);
        solo.finishOpenedActivities();
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

