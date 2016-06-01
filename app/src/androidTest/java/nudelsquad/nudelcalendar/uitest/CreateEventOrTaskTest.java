package nudelsquad.nudelcalendar.uitest;

import android.content.Context;
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
    private Context context;

    public CreateEventOrTaskTest() {
        super(MainActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());
        context = getActivity().getApplicationContext();

        View addEventOrTask = getActivity().findViewById(R.id.add_btn);
        solo.clickOnView(addEventOrTask);
        solo.sleep(500);
    }


    public void testSearchTextTaskOrEvent()
    {
        boolean event = solo.searchText(context.getString(R.string.event));
        assertTrue(event);
        boolean task = solo.searchText(context.getString(R.string.task));
        assertTrue(task);
    }


    public void testButtonDiscardTask()
    {
        boolean b;
        solo.clickOnText(context.getString(R.string.task));
        solo.sleep(100);

        solo.enterText(0,"Lernen-Task");
        solo.enterText(1,"12-05-2010");
        solo.clickOnImageButton(1);  // press OK on Date Selector
        solo.clickOnCheckBox(0); // activate Reminder Checkbox
        solo.enterText(2,"Trallalala dies ist Testtext");
        solo.sleep(100);
        solo.enterText(3,"#45454545");
        solo.clickOnImageButton(1);
        solo.sleep(500);

        b = solo.searchText("Lernen-Task");
        assertTrue(b);
        b = solo.searchText("12-05-2010");
        assertTrue(b);
        b = solo.searchText("Trallalala dies ist Testtext");
        assertTrue(b);
        b = solo.searchText("#45454545");
        assertTrue(b);

        solo.clickOnButton(context.getString(R.string.cancel));
        solo.sleep(100);
    }



    public void testButtonCreateTask()
    {
        boolean b;
        solo.clickOnText(context.getString(R.string.task));
        solo.sleep(100);

        solo.enterText(0,"Lernen-Task");
        solo.enterText(1,"12-05-2010");
        solo.clickOnImageButton(1);  // press OK on Date Selector
        solo.clickOnCheckBox(0); // activate Reminder Checkbox
        solo.enterText(2,"Trallalala dies ist Testtext");
        solo.sleep(100);
        solo.enterText(3,"#45454545");
        solo.clickOnImageButton(1);
        solo.sleep(500);

        b = solo.searchText("Lernen-Task");
        assertTrue(b);
        b = solo.searchText("12-05-2010");
        assertTrue(b);
        b = solo.searchText("Trallalala dies ist Testtext");
        assertTrue(b);
        b = solo.searchText("#45454545");
        assertTrue(b);


        b = solo.searchText(context.getString(R.string.save));
        assertTrue(b);
        solo.clickOnButton(context.getString(R.string.save));
        solo.sleep(100);

        b = solo.searchText(context.getString(R.string.yes));
        assertTrue(b);
        solo.clickOnButton(context.getString(R.string.yes));
        solo.sleep(100);
    }


    public void testButtonCreateEvent()
    {
        solo.clickOnText(context.getString(R.string.event));
        solo.sleep(500);

        boolean b;

        solo.enterText(0,"Fallschirmspringen");
        solo.enterText(1,"12-05-2010");
        solo.sleep(100);
        solo.clickOnImageButton(0); // Chancel to close window
        solo.sleep(100);
        solo.enterText(2,"12:12 PM");
        solo.sleep(100);
        solo.clickOnImageButton(0);
        solo.sleep(100);
        solo.enterText(3,"08:08 AM");
        solo.sleep(100);
        solo.clickOnImageButton(0);
        solo.sleep(100);
        solo.enterText(4,"Austria-Graz");
        solo.enterText(5,"Sport Event");
        solo.enterText(6,"#23434343");
        solo.sleep(100);
        solo.clickOnImageButton(0);
        solo.sleep(100);



        // Test Inputs
        b = solo.searchText("Fallschirmspringen");
        assertTrue(b);
        b = solo.searchText("12-05-2010");
        assertTrue(b);
        b = solo.searchText("12:12 PM");
        assertTrue(b);
        b = solo.searchText("08:08 AM");
        assertTrue(b);
        b = solo.searchText("Austria-Graz");
        assertTrue(b);
        b = solo.searchText("Sport Event");
        assertTrue(b);
        b = solo.searchText("#23434343");
        assertTrue(b);
        

        solo.clickOnButton(context.getString(R.string.save));
        solo.sleep(100);
        solo.clickOnButton(context.getString(R.string.yes));
        solo.sleep(100);
    }

    public void testButtonDiscardEvent()
    {
        solo.clickOnText(context.getString(R.string.event));
        solo.sleep(500);

        boolean b;
        solo.enterText(0,"Fallschirmspringen");
        solo.enterText(1,"12-05-2010");
        solo.sleep(100);
        solo.clickOnImageButton(0); // Chancel to close window
        solo.sleep(100);
        solo.enterText(2,"12:12 PM");
        solo.sleep(100);
        solo.clickOnImageButton(0);
        solo.sleep(100);
        solo.enterText(3,"08:08 AM");
        solo.sleep(100);
        solo.clickOnImageButton(0);
        solo.sleep(100);
        solo.enterText(4,"Austria-Graz");
        solo.enterText(5,"Sport Event");
        solo.enterText(6,"#23434343");
        solo.clickOnImageButton(0);

        solo.sleep(100);
        solo.clickOnButton(context.getString(R.string.cancel));

        b = solo.searchText("Fallschirmspringen");
        assertFalse(b);
        b = solo.searchText("12-05-2010");
        assertFalse(b);
        b = solo.searchText("12:12 PM");
        assertFalse(b);
        b = solo.searchText("08:08 AM");
        assertFalse(b);
        b = solo.searchText("Austria-Graz");
        assertFalse(b);
        b = solo.searchText("Sport Event");
        assertFalse(b);
        b = solo.searchText("#23434343");
        assertFalse(b);
    }

    public void testDateWidget()
    {
        // Widget does not relly work
        solo.clickOnText(context.getString(R.string.event));
        solo.sleep(200);
        solo.clickOnEditText(1);
        solo.sleep(100);
        solo.clickOnEditText(1);
        solo.sleep(200);

        // missing part
    }




    public void testClockWidget()
    {

        solo.clickOnText(context.getString(R.string.event));
        solo.sleep(200);
        solo.clickOnEditText(2);
        solo.sleep(100);
        solo.clickOnEditText(2);
        solo.sleep(200);

        // missing part
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
