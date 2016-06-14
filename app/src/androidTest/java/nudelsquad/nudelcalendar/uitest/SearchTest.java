package nudelsquad.nudelcalendar.uitest;

import android.app.Instrumentation;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.test.ActivityInstrumentationTestCase2;
import android.test.RenamingDelegatingContext;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import com.robotium.solo.Solo;

import nudelsquad.nudelcalendar.DBHandler;
import nudelsquad.nudelcalendar.Event;
import nudelsquad.nudelcalendar.MainActivity;
import nudelsquad.nudelcalendar.R;
import nudelsquad.nudelcalendar.Task;

/**
 * Created by Benjamin Waser on 13.06.2016.
 */
public class SearchTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private Solo solo;
    private DBHandler dbh;

    public SearchTest()
    {
        super(MainActivity.class);
    }

    public void testInput()
    {

    }

    public void testLongClickAtFirstItem()
    {
        View searchinput = getActivity().findViewById(R.id.searchView);
        solo.clickOnView(searchinput);

        //type event or task part
        solo.typeText(0,"t");
        solo.sleep(500);

        solo.clickLongInList(0);
        solo.sleep(500);

        // Search for name of event or task
        Boolean b = solo.searchText("ev1.getName()");
        assertTrue(b);
    }


    public void setUp() throws Exception
    {
        solo = new Solo(getInstrumentation(), getActivity());

      /*  Event ev1 = new Event("TestEvent", "17:00", "18:00", "18-05-2016", "TestType", "TestLocation", 123, "");
        Event ev2 = new Event(1, "TestEvent1", "17:01", "18:01", "19-05-2016", "TestType1", "TestLocation1", 124, "");
        Task task1 = new Task("TestTask", "18.05.2016", "blabla", 123, 1, false);
        Task task2 = new Task("TestTask2", "18.05.2016", "blabla", 123, -1, false);
        Task task3 = new Task(1, "TestTask1", "19.05.2016", "blabla1", 124, 2, true);
        Task task4 = new Task("TestTask", "18.05.2016", "blabla", 123, 1, false);
        Task task5 = new Task("TestTask2", "18.05.2016", "blabla", 123, -1, false);
        dbh = new DBHandler(getInstrumentation().getContext());
        dbh.onCreate(SQLiteDatabase.openDatabase(dbh.getDatabaseName(),null,(SQLiteDatabase.OPEN_READONLY | SQLiteDatabase.NO_LOCALIZED_COLLATORS)));

        SQLiteDatabase a;

        dbh.resetDatabase();

        dbh.addEvent(ev1);
        dbh.addEvent(ev2);
        dbh.addTask(task1);
        dbh.addTask(task2);
        dbh.addTask(task3);
        dbh.addTask(task4);
        dbh.addTask(task5);
*/

        View search = getActivity().findViewById(R.id.btn_search);
        solo.clickOnView(search);

        solo.sleep(500);

    }

}
