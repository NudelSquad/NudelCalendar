package nudelsquad.nudelcalendar.uitest;

import android.graphics.Color;
import android.graphics.Point;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.TextView;

import com.robotium.solo.Solo;
import com.roomorama.caldroid.CaldroidFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import nudelsquad.nudelcalendar.DBHandler;
import nudelsquad.nudelcalendar.Event;
import nudelsquad.nudelcalendar.MainActivity;
import nudelsquad.nudelcalendar.R;

/**
 * Created by emanuel on 20/04/16.
 */
public class MonthViewTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private Solo solo;
    private DBHandler dbHandler;


    public MonthViewTest() {
        super(MainActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        setupDB();
        solo = new Solo(getInstrumentation(), getActivity());
        View viewById = getActivity().findViewById(R.id.btn_month);
        solo.clickOnView(viewById);
        solo.sleep(500);
    }

    private void setupDB() {
        dbHandler=new DBHandler(getActivity().getBaseContext());
        Date time = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String today = formatter.format(time);
        dbHandler.addEvent(new Event("Event 1", "9:00 AM", "12:00 AM", today, "party", "home", Color.GREEN));
        dbHandler.addEvent(new Event("Event 2", "07:00 AM", "9:00 AM", today, "lecture", "uni", Color.BLUE));
    }

    public void test(){
        String day = String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        boolean b;
        b = solo.searchText(day);
        assertTrue(b);
        TextView text = solo.getText(day);
        int[] xy = new int[2];
        text.getLocationOnScreen(xy);
        solo.clickOnScreen(xy[0],xy[1]);
        final int viewWidth = text.getWidth();
        final int viewHeight = text.getHeight();
        final float x = xy[0] + (viewWidth / 2.0f);
        final float y = xy[1] + (viewHeight / 2.0f);

//        solo.clickOnScreen(x,y);
//        solo.clickOnScreen(x,y);

        solo.clickInList(3,2);



        solo.sleep(5000);


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


}
