package nudelsquad.nudelcalendar.uitest;

import android.app.admin.DeviceAdminInfo;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.test.ActivityInstrumentationTestCase2;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.robotium.solo.Solo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import nudelsquad.nudelcalendar.DBHandler;
import nudelsquad.nudelcalendar.MainActivity;
import nudelsquad.nudelcalendar.R;
import nudelsquad.nudelcalendar.Task;

/**
 * Created by waser2 on 18.05.2016.
 */
public class CreateEventOrTaskTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private Solo solo;
    private Context context;
    private DBHandler dbHandler;

    public CreateEventOrTaskTest() {
        super(MainActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());
        context = getActivity().getApplicationContext();
        dbHandler=new DBHandler(context);

        SecurityManager securityManager = new SecurityManager();
        View addEventOrTask = getActivity().findViewById(R.id.add_btn);
        solo.clickOnView(addEventOrTask);
        solo.sleep(500);
    }


    public void testSearchTextTaskOrEvent() {
        boolean event = solo.searchText(context.getString(R.string.event));
        assertTrue(event);
        boolean task = solo.searchText(context.getString(R.string.task));
        assertTrue(task);
    }


    public void testButtonDiscardTask() {
        boolean b;
        solo.clickOnText(context.getString(R.string.task));
        solo.sleep(100);

        solo.typeText(0, "Lernen-Task");
        solo.typeText(1, "12-05-2010");
        solo.clickOnCheckBox(0); // activate Reminder Checkbox
        solo.typeText(2, "Trallalala dies ist Testtext");
        solo.sleep(100);
        solo.typeText(3, "#45454545");
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


    public void testButtonCreateTask() {
        boolean b;
        solo.clickOnText(context.getString(R.string.task));
        solo.sleep(100);


        solo.typeText(0, "Lernen-Task");
        solo.typeText(1, "12-05-2010");
        solo.clickOnCheckBox(0); // activate Reminder Checkbox
        solo.typeText(2, "Trallalala dies ist Testtext");
        solo.sleep(100);
        solo.typeText(3, "#45454545");
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


    public void testButtonCreateEvent() {
        solo.clickOnText(context.getString(R.string.event));
        solo.sleep(500);

        boolean b;

        solo.typeText(0, "Fallschirmspringen");
        solo.typeText(1, "12-05-2010");
        solo.typeText(2, "12:12");
        solo.typeText(3, "08:08");
        solo.typeText(4, "Austria-Graz");
        solo.typeText(5, "Sport Event");
        solo.typeText(6, "#23434343");


        // Test Inputs
        b = solo.searchText("Fallschirmspringen");
        assertTrue(b);
        b = solo.searchText("12-05-2010");
        assertTrue(b);
        b = solo.searchText("12:12");
        assertTrue(b);
        b = solo.searchText("08:08");
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

        solo.typeText(0, "Fallschirmspringen");
        solo.typeText(1, "12-05-2010");
        solo.typeText(2, "12:12");
        solo.typeText(3, "08:08");
        solo.typeText(4, "Austria-Graz");
        solo.typeText(5, "Sport Event");
        solo.typeText(6, "#23434343");

        solo.sleep(100);
        solo.clickOnButton(context.getString(R.string.cancel));

        b = solo.searchText("Fallschirmspringen");
        assertFalse(b);
        b = solo.searchText("12-05-2010");
        assertFalse(b);
        b = solo.searchText("12:12");
        assertFalse(b);
        b = solo.searchText("08:08");
        assertFalse(b);
        b = solo.searchText("Austria-Graz");
        assertFalse(b);
        b = solo.searchText("Sport Event");
        assertFalse(b);
        b = solo.searchText("#23434343");
        assertFalse(b);
    }

    public void testDateWidget() {
        // Widget does not relly work
        solo.clickOnText(context.getString(R.string.event));
        solo.sleep(200);
        solo.clickOnEditText(1);
        solo.sleep(100);
        solo.clickOnEditText(1);
        solo.sleep(200);

        // missing part
    }


    public void testClockWidget() {

        solo.clickOnText(context.getString(R.string.event));
        solo.sleep(200);
        solo.clickOnEditText(2);
        solo.sleep(100);
        solo.clickOnEditText(2);
        solo.sleep(200);

        // missing part
    }

  /*  public void testRecordPlay() {
        solo.clickOnText(context.getString(R.string.event));
        solo.sleep(500);
        View v = getActivity().findViewById(R.id.btn_record);
        solo.clickOnView(v);
        solo.sleep(500);

        boolean b;

        b = solo.searchText(getActivity().getBaseContext().getString(R.string.ok));
        if (b) {
            solo.clickOnText(getActivity().getBaseContext().getString(R.string.ok));
            solo.sleep(100);
//            ViewGroup viewGroup = (ViewGroup) getActivity().findViewById(android.R.id.content);
//            ViewGroup vg = (ViewGroup) viewGroup.getChildAt(0);

            for(View v1: solo.getViews()) {
                if(v instanceof Button) {
                    if( ((Button)v1).getText().toString().equalsIgnoreCase("allow") )
                    {
                        solo.clickOnView(v1);
                        solo.sleep(300);
                        break;
                    }
                }

            }


            solo.clickOnScreen(784, 1150);
            solo.clickOnScreen(784, 1150);
            solo.clickOnScreen(784, 1150);

            solo.sleep(500);
        }
        solo.clickOnView(v);

        b = solo.searchText(getActivity().getBaseContext().getString(R.string.ok));
        if (b) {
            solo.clickOnText(getActivity().getBaseContext().getString(R.string.ok));
            solo.sleep(100);

            solo.sleep(500);
        }

        solo.clickOnView(v);


        v = getActivity().findViewById(R.id.btn_play);
        solo.sleep(500);
        solo.clickOnView(v);
        solo.sleep(500);
        solo.clickOnView(v);
        solo.sleep(200);
    }*/

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

    public void testEditEvent() {

        dbHandler.resetDatabase();
        solo.clickOnText(context.getString(R.string.event));
        solo.sleep(500);
        Date time = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String today = formatter.format(time);

        boolean b;

        solo.enterText(0, "Fallschirmspringen");
        solo.enterText(1, today);
        solo.sleep(100);
        solo.clickOnImageButton(0); // Chancel to close window
        solo.sleep(100);
        solo.enterText(2, "12:12");
        solo.sleep(100);
        solo.clickOnImageButton(0);
        solo.sleep(100);
        solo.enterText(3, "08:08");
        solo.sleep(100);
        solo.clickOnImageButton(0);
        solo.sleep(100);
        solo.enterText(4, "Austria-Graz");
        solo.enterText(5, "Sport Event");
        solo.enterText(6, "#23434343");
        solo.sleep(100);
        solo.clickOnImageButton(0);
        solo.sleep(100);


        solo.clickOnButton(context.getString(R.string.save));
        solo.sleep(100);
        solo.clickOnButton(context.getString(R.string.yes));
        solo.sleep(100);
        solo.clickInList(1);
        solo.clickOnButton(context.getString(R.string.edit));


        solo.sleep(100);
        solo.clearEditText(2);
        solo.enterText(2, "12:13");
        solo.sleep(100);
        solo.clickOnImageButton(0);
        solo.sleep(100);
        solo.clearEditText(3);
        solo.enterText(3, "08:09");
        solo.sleep(100);
        solo.clickOnImageButton(0);
        solo.sleep(100);
        solo.clearEditText(4);
        solo.enterText(4, "Austria-Graz1");
        solo.clearEditText(5);
        solo.enterText(5, "Sport Event1");
        solo.clearEditText(6);
        solo.enterText(6, "#23434343");
        solo.sleep(100);
        solo.clickOnImageButton(0);
        solo.sleep(100);


        // Test Inputs
        b = solo.searchText("Fallschirmspringen");
        assertTrue(b);
        b = solo.searchText(today);
        assertTrue(b);
        b = solo.searchText("12:13");
        assertTrue(b);
        b = solo.searchText("08:09");
        assertTrue(b);
        b = solo.searchText("Austria-Graz1");
        assertTrue(b);
        b = solo.searchText("Sport Event1");
        assertTrue(b);
        b = solo.searchText("#23434343");
        assertTrue(b);


        solo.clickOnButton(context.getString(R.string.save));

    }

    public void testEditEventAndAddTask() {

        dbHandler.resetDatabase();
        solo.clickOnText(context.getString(R.string.event));
        solo.sleep(500);
        Date time = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String today = formatter.format(time);

        boolean b;

        solo.enterText(0, "Fallschirmspringen");
        solo.enterText(1, today);
        solo.sleep(100);
        solo.clickOnImageButton(0); // Chancel to close window
        solo.sleep(100);
        solo.enterText(2, "12:12");
        solo.sleep(100);
        solo.clickOnImageButton(0);
        solo.sleep(100);
        solo.enterText(3, "08:08");
        solo.sleep(100);
        solo.clickOnImageButton(0);
        solo.sleep(100);
        solo.enterText(4, "Austria-Graz");
        solo.enterText(5, "Sport Event");
        solo.enterText(6, "#23434343");
        solo.sleep(100);
        solo.clickOnImageButton(0);
        solo.sleep(100);


        solo.clickOnButton(context.getString(R.string.save));
        solo.sleep(100);
        solo.clickOnButton(context.getString(R.string.yes));
        solo.sleep(100);
        solo.clickInList(1);
        solo.clickOnButton(context.getString(R.string.edit));


        solo.sleep(100);
        solo.clearEditText(2);
        solo.enterText(2, "12:13");
        solo.sleep(100);
        solo.clickOnImageButton(0);
        solo.sleep(100);
        solo.clearEditText(3);
        solo.enterText(3, "08:09");
        solo.sleep(100);
        solo.clickOnImageButton(0);
        solo.sleep(100);
        solo.clearEditText(4);
        solo.enterText(4, "Austria-Graz1");
        solo.clearEditText(5);
        solo.enterText(5, "Sport Event1");
        solo.clearEditText(6);
        solo.enterText(6, "#23434343");
        solo.sleep(100);
        solo.clickOnImageButton(0);
        solo.sleep(100);


        solo.clickOnButton(context.getString(R.string.add_task));

        solo.clickOnText(context.getString(R.string.task));
        solo.sleep(100);


        solo.enterText(0, "Lernen-Task");
        solo.enterText(1, "12-05-2010");
        solo.clickOnImageButton(1);  // press OK on Date Selector
        solo.clickOnCheckBox(0); // activate Reminder Checkbox
        solo.enterText(2, "Trallalala dies ist Testtext");
        solo.sleep(100);
        solo.enterText(3, "#45454545");
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



        solo.clickOnButton(context.getString(R.string.save));

    }


    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
    }

}
