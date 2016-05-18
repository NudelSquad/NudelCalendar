package nudelsquad.nudelcalendar.uitest;

import android.graphics.Color;
import android.graphics.Point;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.ListView;

import com.robotium.solo.Solo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import nudelsquad.nudelcalendar.DBHandler;
import nudelsquad.nudelcalendar.MainActivity;
import nudelsquad.nudelcalendar.R;
import nudelsquad.nudelcalendar.Task;

/**
 * Created by waser2 on 18.05.2016.
 */
public class TaskBoardViewTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private Solo solo;
    private DBHandler dbh;

    public TaskBoardViewTest() {
        super(MainActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());
        dbh = new DBHandler(getActivity().getBaseContext());
        setUpDatabase();

        openNavigationDrawer();
        solo.clickOnText("Task Board");
        solo.sleep(500);

    }

    public void setUpDatabase()
    {
        dbh.resetDatabase();

        Date time = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String today = formatter.format(time);

        dbh.addTask(new Task("Fußballspiel",today,"Dies ist ein testtext und nicht sehr aussagekräftig",Color.BLUE,1,true));
        dbh.addTask(new Task("Programmieren",today,"Dies ist ein testtext und nicht sehr aussagekräftig",Color.RED,2,false));
        dbh.addTask(new Task("Radfahren",today,"Heute werde ich noch ordentlich anradeln",Color.GREEN,3,true));
        dbh.addTask(new Task("Fußballspiel",today,"Dies ist ein testtext und nicht sehr aussagekräftig",Color.BLUE,2,false));
        dbh.addTask(new Task("Fußballspiel",today,"Dies ist ein testtext und nicht sehr aussagekräftig",Color.BLUE,5,false));
        dbh.addTask(new Task("Fußballspiel",today,"Dies ist ein testtext und nicht sehr aussagekräftig",Color.BLUE,6,true));

    }

    public void testSearchHeader()
    {
        boolean b;
        b=solo.searchText("Upcoming Task");
        assertTrue(b);
    }

    public void testClickOnHeader()
    {
        View taskboardheader = getActivity().findViewById(R.id.tv_taskboardheader);
        solo.clickOnView(taskboardheader);
        solo.sleep(500);
    }

    public void testFindTaskAttributesInList()
    {
        boolean b;
        b = solo.searchText("Fußball");
        assertFalse(b);
    }



    public void testClickOnList() {
        View taskboardlist = getActivity().findViewById(R.id.taskboard_list);
        solo.clickOnView(taskboardlist);


    }

    public void testClickAtListItem() {
        View taskboardlist = getActivity().findViewById(R.id.taskboard_list);
        solo.clickInList(1);

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