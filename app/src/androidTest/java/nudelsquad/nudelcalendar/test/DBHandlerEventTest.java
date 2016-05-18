package nudelsquad.nudelcalendar.test;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;
import android.util.Log;


import java.util.List;

import nudelsquad.nudelcalendar.DBHandler;
import nudelsquad.nudelcalendar.Event;

/**
 * Created by Marco on 18.05.2016.
 */
public class DBHandlerEventTest extends AndroidTestCase {
    Event testEvent = new Event("TestEvent", "17:00", "18:00", "18.05.2016", "TestType", "TestLocation", -1);
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
            Log.v("Event", e.toString());
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
}
