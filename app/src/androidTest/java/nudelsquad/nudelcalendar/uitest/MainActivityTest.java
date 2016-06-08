package nudelsquad.nudelcalendar.uitest;

import android.graphics.Point;
import android.support.v4.widget.DrawerLayout;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.ListView;

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
        solo.goBack();

    }


    public void testButtonToday() {
        View v = getActivity().findViewById(R.id.btn_day);
        solo.clickOnView(v);
        solo.sleep(500);
    }

    public void testButtonWeek() {
        View v = getActivity().findViewById(R.id.btn_week);
        solo.clickOnView(v);
    }

    public void testButtonMonths() {
        View v = getActivity().findViewById(R.id.btn_month);
        solo.clickOnView(v);
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testOpenHome(){
        openNavigationDrawer();
        solo.clickOnText(getActivity().getApplicationContext().getString(R.string.drawer_home));
        solo.sleep(500);
    }

    public void testOpenDay(){
        openNavigationDrawer();
        solo.clickOnText(getActivity().getApplicationContext().getString(R.string.drawer_day));
        solo.sleep(500);
    }

    public void testOpenWeek(){
        openNavigationDrawer();
        solo.clickOnText(getActivity().getApplicationContext().getString(R.string.drawer_week), 2);
        solo.sleep(500);
    }

    public void testOpenMonth(){
        openNavigationDrawer();
        solo.clickOnText(getActivity().getApplicationContext().getString(R.string.drawer_month), 2);
        solo.sleep(500);
    }

    public void testOpenTaskBoard(){
        openNavigationDrawer();
        solo.clickOnText(getActivity().getApplicationContext().getString(R.string.drawer_taskboard));
        solo.sleep(500);
    }

    public void testOpenSettings(){
        openNavigationDrawer();
        solo.clickOnText(getActivity().getApplicationContext().getString(R.string.drawer_settings));
        solo.sleep(500);
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
