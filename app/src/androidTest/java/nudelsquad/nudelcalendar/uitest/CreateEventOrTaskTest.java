package nudelsquad.nudelcalendar.uitest;

import android.graphics.Color;
import android.graphics.Point;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;

import com.robotium.solo.Solo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import nudelsquad.nudelcalendar.MainActivity;
import nudelsquad.nudelcalendar.R;
import nudelsquad.nudelcalendar.Task;

/**
 * Created by waser2 on 18.05.2016.
 */
public class CreateEventOrTaskTest  extends ActivityInstrumentationTestCase2<MainActivity> {

    private Solo solo;

    public CreateEventOrTaskTest() {
        super(MainActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());
        openNavigationDrawer();
        View addEventOrTask = getActivity().findViewById(R.id.add_btn);
        solo.clickOnView(addEventOrTask);
        solo.sleep(500);
    }


    public void testSearchTextTaskOrEvent()
    {
        boolean event = solo.searchText("Event");
        assertTrue(event);
        boolean task = solo.searchText("Task");
        assertTrue(task);
    }


    public void testClickOnEventandCreateIt()
    {
        View addEventOrTask = getActivity().findViewById(R.id.add_btn);
        solo.clickOnView(addEventOrTask);
        solo.sleep(500);

        solo.clickOnText("Event");

        solo.clickOnEditText(0);
        solo.enterText(0,"Eventname");

        boolean test = solo.searchText("Eventname");
        assertTrue(test);



        solo.clickOnButton("SAVE");


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



    public void tearDown() throws Exception
    {
        super.tearDown();
    }

}
