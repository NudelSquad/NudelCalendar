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
        boolean b = false;
        solo.clickOnText("Event");
        solo.sleep(500);

        //testbtnDiscard();



        // First Edittext-field
        solo.clickOnEditText(0);
        solo.sleep(500);
        solo.enterText(0,"Fallschirmspringen");
        boolean test = solo.searchText("Fallschirmspringen");
        assertTrue(test);

        // SecondEditTextfield - Date Selector in Format "12-05-2010"
        solo.clickOnEditText(1);
        solo.clickOnEditText(1);
        solo.sleep(500);

        solo.clickOnButton(1); // Press OK
        solo.sleep(1000);

        // Third Edittext-field - Begin of Event in Format "11:20 AM"
        solo.enterText(2,"12:12 PM");

        solo.sleep(500);
        // testClockWidget(); //Detailed Clock Widget Select don't work actually


        // Fourth Edittext-field - End of Event in Format "11:20 AM"
        solo.enterText(2,"08:08 AM");
        solo.sleep(500);
        // testClockWidget(); //Detailed Clock Widget Select don't work actually


        // Fifth EditText-field - Place
        solo.enterText(4,"Austria-Graz");
        b = solo.searchText("Austria-Graz");
        assertTrue(b);
        solo.sleep(500);

        // Sixt EditText-field - Type
        solo.enterText(5,"Sport Event");
        b = solo.searchText("Austria-Graz");
        assertTrue(b);
        solo.sleep(500);


        solo.clickOnButton("SAVE");


        // test Button Discard

    }

    void testbtnDiscard()
    {
        boolean b = false;
        solo.enterText(0,"Fallschirmspringen");
        solo.enterText(1,"12-05-2010");
        solo.sleep(1000);
        solo.clickOnImageButton(0);
        solo.enterText(2,"12:12 PM");
        solo.clickOnImageButton(0);
        solo.enterText(3,"08:08 AM");
        solo.clickOnImageButton(0);
        solo.enterText(4,"Austria-Graz");
        solo.enterText(5,"Sport Event");
        solo.enterText(6,"#23434343");
        solo.clickOnImage(1);

        solo.sleep(1000);
        solo.clickOnButton("DISCARD");

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

    public void testClockWidget()
    {

        // solo.clickOnEditText(2);
        //solo.clickOnEditText(2);
        //solo.sleep(500);
        //solo.clickOnText("11");
        //solo.clickOnText("45");
        //solo.clickOnText("OK");
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
