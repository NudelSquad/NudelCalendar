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
        solo.clickOnText("Settings");
        solo.sleep(500);

    }


    public void testSwitcher() {
        View swLS = getActivity().findViewById(R.id.swLS);
        View swRem = getActivity().findViewById(R.id.swRem);
        solo.clickOnView(swLS);
        solo.clickOnView(swRem);
    }

    public void testComboBox() {
        View view = getActivity().findViewById(R.id.spMuteFor);
        solo.clickOnView(view);
        boolean b;

        b = solo.searchText("15");
        assertTrue(b);
        b = solo.searchText("30");
        assertTrue(b);
        b = solo.searchText("45");
        assertTrue(b);
        b = solo.searchText("60");
        assertTrue(b);
        b = solo.searchText("75");
        assertTrue(b);

        solo.clickInList(2);
        solo.sleep(500);


    }

    public void testButton() {
        solo.clickOnButton("Save");
        solo.sleep(500);
        boolean saved = solo.searchText("Saved");
        assertTrue(saved);
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
