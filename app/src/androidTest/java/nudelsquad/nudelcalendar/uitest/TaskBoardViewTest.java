package nudelsquad.nudelcalendar.uitest;

import android.graphics.Point;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.ListView;

import com.robotium.solo.Solo;

import java.util.List;

import nudelsquad.nudelcalendar.MainActivity;
import nudelsquad.nudelcalendar.R;

/**
 * Created by waser2 on 18.05.2016.
 */
public class TaskBoardViewTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private Solo solo;


    public TaskBoardViewTest() {
        super(MainActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());
        openNavigationDrawer();
        solo.clickOnText("Task Board");
        solo.sleep(500);

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



    public void testClickOnList() {
        View taskboardlist = getActivity().findViewById(R.id.taskboard_list);
        solo.clickOnView(taskboardlist);

    }

    public void testClickAtListItem() {
        View taskboardlist = getActivity().findViewById(R.id.taskboard_list);
        solo.clickInList(2);
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