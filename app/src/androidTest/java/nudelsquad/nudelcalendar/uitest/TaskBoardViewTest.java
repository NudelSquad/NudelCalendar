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
        solo.clickOnText(getActivity().getResources().getString(R.string.drawer_taskboard));
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
        dbh.addTask(new Task("Radfahrenlalalalso ist dieser TEsT",today,"Heute werde ich noch ordentlich anradeln",Color.GREEN,3,true));
        dbh.addTask(new Task("Fußballspiel",today,"Dies ist ein testtext und nicht sehr aussagekräftig",Color.YELLOW,4,true));
        dbh.addTask(new Task("Essen kochen",today,"Dies ist ein testtext und nicht sehr aussagekräftig",Color.BLACK,5,false));
        dbh.addTask(new Task("ANDROID pro werden",today,"Dies ist eine Feststellung und nicht sehr aussagekräftig",Color.GRAY,6,true));

    }

    public void testSearchHeader()
    {
        boolean b;
        b=solo.searchText(getActivity().getResources().getString(R.string.upcoming_tasks));
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
        b = solo.searchText("Radfahrenlalalalso");
        assertTrue(b);
    }

    public void testClickItemLongAndSeeText()
    {
        boolean b;
        solo.clickLongInList(1);
        solo.sleep(500);
        b = solo.searchText("Dies ist ein testtext und nicht sehr aussagekräftig");
        assertTrue(b);
    }




    public void testClickOnList() {

        solo.clickOnText("Fußballspiel");
        boolean fußballspiel = solo.searchText("Fußballspiel");
        assertTrue(fußballspiel);
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