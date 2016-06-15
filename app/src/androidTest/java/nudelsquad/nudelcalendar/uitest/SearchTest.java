package nudelsquad.nudelcalendar.uitest;

import android.app.Instrumentation;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.test.ActivityInstrumentationTestCase2;
import android.test.RenamingDelegatingContext;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import com.robotium.solo.Solo;

import java.util.ArrayList;

import nudelsquad.nudelcalendar.DBHandler;
import nudelsquad.nudelcalendar.Event;
import nudelsquad.nudelcalendar.MainActivity;
import nudelsquad.nudelcalendar.R;
import nudelsquad.nudelcalendar.Task;
import nudelsquad.nudelcalendar.search.SearchItem;

/**
 * Created by Benjamin Waser on 13.06.2016.
 */
public class SearchTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private Solo solo;
    private DBHandler dbh;
    private View searchbtnview;

    public SearchTest()
    {
        super(MainActivity.class);
    }


    public void testMuchDatabaseEntrys()
    {
        fillDatabaseWithEvents(100);
        fillDatabaseWithTasks(100);

        solo.sleep(500);
        solo.clickOnView(searchbtnview);
        solo.sleep(1000);

        View searchinput = getActivity().findViewById(R.id.searchView);
        solo.clickOnView(searchinput);

        solo.typeText(0,"100");
        solo.sleep(500);

        // Click on Task number 100
        solo.clickLongInList(1);

        Boolean b = solo.searchText("100");
        assertTrue(b);
    }

    public void testSearchSpecificEventItem()
    {
        fillDatabaseWithEvents(15);
        fillDatabaseWithTasks(15);

        solo.sleep(500);
        solo.clickOnView(searchbtnview);

        solo.sleep(1000);

        View searchinput = getActivity().findViewById(R.id.searchView);
        solo.clickOnView(searchinput);

        //type event or task part
        solo.typeText(0,"TestEvent7");
        solo.sleep(500);

        solo.clickLongInList(0);
        solo.sleep(500);

        // Search for name of event or task
        Boolean b = solo.searchText("TestEvent7");
        assertTrue(b);
    }
    public void testSearchSpecificTaskItem()
    {
        fillDatabaseWithEvents(15);
        fillDatabaseWithTasks(15);

        solo.sleep(500);
        solo.clickOnView(searchbtnview);

        solo.sleep(1000);

        View searchinput = getActivity().findViewById(R.id.searchView);
        solo.clickOnView(searchinput);

        //type event or task part
        solo.typeText(0,"TestTask9");
        solo.sleep(500);

        solo.clickLongInList(0);
        solo.sleep(500);

        // Search for name of event or task
        Boolean b = solo.searchText("TestTask9");
        assertTrue(b);
    }


    public void fillDatabaseWithEvents(int number_of_events)
    {
        for(int i = 1; i <= number_of_events; i ++)
        {
            dbh.addEvent(new Event(i, "TestEvent" + i , "17:00", "18:00", "19-06-2016", "testtext", "TestLocation" + i, 124, ""));
        }
    }

    public void fillDatabaseWithTasks(int number_of_tasks)
    {
        for(int i = 1; i <= number_of_tasks; i ++)
        {
            dbh.addTask(new Task("TestTask"+i, "26.06.2016", "TestText", 123, 1, false));
        }
    }

    public void testEmptyDatabase()
    {
        solo.sleep(500);
        solo.clickOnView(searchbtnview);
        solo.sleep(1000);
        View searchinput = getActivity().findViewById(R.id.searchView);
        solo.clickOnView(searchinput);
        assertTrue(true);
    }

    public void setUp() throws Exception
    {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());
        dbh = new DBHandler(getActivity().getBaseContext());
        dbh.resetDatabase();
        searchbtnview = getActivity().findViewById(R.id.btn_search);
    }

}
