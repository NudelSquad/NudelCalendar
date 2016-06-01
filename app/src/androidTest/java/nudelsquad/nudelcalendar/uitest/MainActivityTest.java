package nudelsquad.nudelcalendar.uitest;

import android.graphics.Point;
import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import nudelsquad.nudelcalendar.MainActivity;
import nudelsquad.nudelcalendar.MonthView;
import nudelsquad.nudelcalendar.R;

/**
 * Created by emanuel on 20/04/16.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private Solo solo;


    public MainActivityTest() {
        super(MainActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());

    }


    public void testButtonToday() {
        solo.clickOnButton(0);
    }

    public void testButtonWeek() {
        solo.clickOnButton(1);
    }

    public void testButtonMonths() {
        solo.clickOnButton(2);
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testOpenHome(){
        openNavigationDrawer();
        solo.clickOnText(getActivity().getApplicationContext().getString(R.string.drawer_home));
    }

    public void testOpenDay(){
        openNavigationDrawer();
        solo.clickOnText(getActivity().getApplicationContext().getString(R.string.drawer_day));
    }

    public void testOpenWeek(){
        openNavigationDrawer();
        solo.clickOnText(getActivity().getApplicationContext().getString(R.string.drawer_week));
    }

    public void testOpenMonth(){
        openNavigationDrawer();
        solo.clickOnText(getActivity().getApplicationContext().getString(R.string.drawer_month));
    }

    public void testClickBackButton(){
        openNavigationDrawer();
        solo.clickOnText(getActivity().getApplicationContext().getString(R.string.drawer_day));
        solo.sleep(500);
        solo.goBack();
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
