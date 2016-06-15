package nudelsquad.nudelcalendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import java.security.acl.LastOwnerException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Clemens on 01.06.2016.
 */
public class AlarmHandler {

    private static AlarmHandler manager = new AlarmHandler();
    PendingIntent pi;
    static int requestCounter=1;

    private Map<Integer, Intent> eventIntentMap = new HashMap<>();
    final static String ID = "id";
    final static String TYPE = "type";
    final static String TASK = "task";
    final static String EVENT = "event";
    final static String TAG = "ARLARMHANDLER";
    private static AlarmManager alarmManager;
    Activity mainActivity;


    public static AlarmHandler getInstance() {
        return manager;
    }

    public void setAlarmManager(AlarmManager alarmManager) {
        AlarmHandler.alarmManager = alarmManager;
    }


    public void addEvent(Event event) {
        Calendar cal = Calendar.getInstance();


        String event_datum = event.getEVENT_DATUM();
        String event_start = event.getEVENT_START();


        int day = Integer.parseInt(event_datum.split("-")[0]);
        int  month= Integer.parseInt(event_datum.split("-")[1]);
        int  year= Integer.parseInt(event_datum.split("-")[2]);
        int hours = Integer.parseInt(event_start.split(":")[0]);
        int minutes = Integer.parseInt(event_start.split(":")[1]);

        cal.set(Calendar.MONTH, month-1);
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.DAY_OF_MONTH, day);

        cal.set(Calendar.HOUR_OF_DAY, hours);
        cal.set(Calendar.MINUTE, minutes);
        cal.set(Calendar.SECOND,0);



        Log.d(TAG, String.valueOf((cal.getTimeInMillis()-System.currentTimeMillis())/1000));
        Log.d(TAG, cal.toString());


        Intent intent = new Intent("com.nudelsquad.Nudelcalendar");
        intent.putExtra("id",event.getEVENT_ID());
        pi = PendingIntent.getBroadcast( mainActivity, requestCounter,intent, 0 );
        alarmManager.set( AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),  pi);

        requestCounter++;
    }





    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
}
