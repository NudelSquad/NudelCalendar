package nudelsquad.nudelcalendar;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHandlerAlarm extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "NudelCal_Data";

    // Events table name
    private static final String TABLE_ALARMS = "alarms";

    // Events Table Columns names
    private static final String KEY_ALARM_ID = "id";
    private static final String KEY_ALARM_TASKID = "task";
    private static final String KEY_ALARM_MINVOR = "minvor";

    public DBHandlerAlarm(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_ALARMS + "("
                + KEY_ALARM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_ALARM_TASKID + " INTEGER,"
                + KEY_ALARM_MINVOR + " INTEGER, " + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ALARMS);

        // Creating tables again
        onCreate(db);
    }

}