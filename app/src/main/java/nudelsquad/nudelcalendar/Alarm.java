package nudelsquad.nudelcalendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;

import nudelsquad.nudelcalendar.Event;

public class Alarm extends BroadcastReceiver {
    private static int requestCounter = 0;
    private final static String TAG = "Alarm";

    @Override
    public void onReceive(Context context, Intent intent) {
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "");
        wl.acquire();

        // Put here YOUR code.
        Log.d(TAG, "yessss");
        Toast.makeText(context, "Alarm !!!!!!!!!!", Toast.LENGTH_LONG).show(); // For example

        wl.release();
    }

    public void SetAlarm(Context context, Event event)

    {

        Calendar cal = Calendar.getInstance();
//        String event_datum = event.getEVENT_DATUM();
//        String event_start = event.getEVENT_START();
//
//
//        int day = Integer.parseInt(event_datum.split("-")[0]);
//        int month = Integer.parseInt(event_datum.split("-")[1]);
//        int year = Integer.parseInt(event_datum.split("-")[2]);
//        int hours = Integer.parseInt(event_start.split(":")[0]);
//        int minutes = Integer.parseInt(event_start.split(":")[1]);
//
//        cal.set(Calendar.MONTH, month);
//        cal.set(Calendar.YEAR, year);
//        cal.set(Calendar.DAY_OF_MONTH, day);
//        cal.set(Calendar.HOUR_OF_DAY, hours);
//        cal.set(Calendar.MINUTE, minutes);
//        cal.set(Calendar.SECOND, 0);


        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent("com.nudelsquad.Nudelcalendar");
//        intent.putExtra("id",event.getEVENT_ID());
//
        PendingIntent pi = PendingIntent.getBroadcast(context, requestCounter++, intent, 0);
        cal.setTimeInMillis(System.currentTimeMillis());

        Log.d(TAG, String.valueOf(cal.getTimeInMillis() + 3000));
        am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis() + 3000, pi); // Millisec * Second * Minute
    }

    public void CancelAlarm(Context context) {
        Intent intent = new Intent(context, Alarm.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
    }
}
