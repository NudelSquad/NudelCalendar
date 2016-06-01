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
        Intent intent = new Intent("com.nudelsquad.Nudelcalendar");
        intent.putExtra("id", requestCounter);
        pi = PendingIntent.getBroadcast( mainActivity, requestCounter,intent, 0 );
        alarmManager.set( AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + 3000, pi );

        requestCounter++;
    }


    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
}
