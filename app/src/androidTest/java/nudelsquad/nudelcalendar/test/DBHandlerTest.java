package nudelsquad.nudelcalendar.test;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;
import java.util.List;

import nudelsquad.nudelcalendar.DBHandler;
import nudelsquad.nudelcalendar.Event;
import nudelsquad.nudelcalendar.Task;

/**
 * Created by Marco on 18.05.2016.
 */
public class DBHandlerTest extends AndroidTestCase {
    Event testEvent = new Event("TestEvent", "17:00", "18:00", "18.05.2016", "TestType", "TestLocation", -1);
    Task testTask = new Task("TestTask", "18.05.2016", "blabla", -1, -1, false);
    DBHandler dbh;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
        dbh = new DBHandler(context);
    }

    public void testAddEvent(){
        dbh.addEvent(testEvent);
    }

    public void testGetEvents(){
        List<Event> events = dbh.getAllEvents();
        for (Event e:events) {
            if (e.getEVENT_COLOR() == -1){
                assertEquals(e.getEVENT_NAME(), testEvent.getEVENT_NAME());
                assertEquals(e.getEVENT_START(), testEvent.getEVENT_START());
                assertEquals(e.getEVENT_END(), testEvent.getEVENT_END());
                assertEquals(e.getEVENT_DATUM(), testEvent.getEVENT_DATUM());
                assertEquals(e.getEVENT_TYPE(), testEvent.getEVENT_TYPE());
                assertEquals(e.getEVENT_LOCATION(), testEvent.getEVENT_LOCATION());
            }
        }
    }

    public void testAddTask(){
        dbh.addTask(testTask);
    }

    public void testGetTasks(){
        List<Task> tasks = dbh.getAllTasks();
        for (Task t:tasks) {
            if (t.getTASK_COLOR() == -1){
                assertEquals(t.getTASK_NAME(), testTask.getTASK_NAME());
                assertEquals(t.getTASK_DATUM(), testTask.getTASK_DATUM());
                assertEquals(t.getTASK_TEXT(), testTask.getTASK_TEXT());
                assertEquals(t.getTASK_EVENTID(), testTask.getTASK_EVENTID());
                assertEquals(t.getTASK_CHECKED(), testTask.getTASK_CHECKED());
            }
        }
    }

}
