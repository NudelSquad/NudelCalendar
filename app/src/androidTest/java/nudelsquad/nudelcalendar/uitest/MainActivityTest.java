package nudelsquad.nudelcalendar.uitest;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import nudelsquad.nudelcalendar.MainActivity;

/**
 * Created by emanuel on 20/04/16.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private Solo mySolo;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        mySolo = new Solo(getInstrumentation(), getActivity());

    }


    public void testButtonToday() {
        mySolo.clickOnButton(0);
    }

    public void testButtonWeek() {
        mySolo.clickOnButton(1);
    }

    public void testButtonMonths() {
        mySolo.clickOnButton(2);
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }



}
