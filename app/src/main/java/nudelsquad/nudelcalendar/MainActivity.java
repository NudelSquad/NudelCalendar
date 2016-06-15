package nudelsquad.nudelcalendar;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Locale;

import nudelsquad.nudelcalendar.search.SearchList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FrameLayout mainFrame;      //Main Frame to switch between frames

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    SharedPreferences sharedPrefs = null;                       //to Save Settings
    private static final String PrefName = "SettingPreferences";
    private static final String Pref_KEY_LANDSC = "LANDSCAPEMODE";
    private static final String Pref_KEY_REMINDER = "REMINDER";


    public static Bundle myBundle = new Bundle();
    PendingIntent pi;
    BroadcastReceiver br;
    AlarmManager am;
    DBHandler dbHandler;
    private TextToSpeech t1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        dbHandler = new DBHandler(getBaseContext());


        //Get Settings
        sharedPrefs = getSharedPreferences(PrefName, 0);
        if(sharedPrefs.getBoolean(Pref_KEY_LANDSC, false)){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        else
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setup();

        AlarmHandler.getInstance().setMainActivity(this);
        AlarmHandler.getInstance().setAlarmManager(am);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ImageButton btn_search = (ImageButton) findViewById(R.id.btn_search);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment fragment = new SearchList();
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.main_frame, fragment);
                transaction.commit();

            }
        });

        myBundle.putString("selectedDate", String.valueOf("n"));




        //OPEN ADD FRAME
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add_btn);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(getString(R.string.creaet_task_question));
                builder.setItems(new String[]{getString(R.string.event), getString(R.string.task)}, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Fragment fragment = null;
                        if (which == 0) {
                            fragment = new CreateEventView(0);
                        }
                        if (which == 1) {
                            fragment = new CreateTaskView(-1);  //-1 wil eigener Task
                        }
                        FragmentManager fm = getSupportFragmentManager();
                        FragmentTransaction transaction = fm.beginTransaction();
                        transaction.replace(R.id.main_frame, fragment);
                        transaction.commit();
                    }
                });
                AlertDialog alert = builder.create(); // The error log points to this line
                alert.show();

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = new StartScreenFrame();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.main_frame, fragment);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Fragment fragment = new SettingsView();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.main_frame, fragment, "settings");
            transaction.commit();
        }

        return super.onOptionsItemSelected(item);
    }

    //Switch between Frames throw menu
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Fragment fragment = new StartScreenFrame();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.main_frame, fragment, "home");
            transaction.commit();

        } else if (id == R.id.nav_day) {
            Fragment fragment = new DayList();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.main_frame, fragment, "day");
            transaction.commit();

        } else if (id == R.id.nav_week) {
            Fragment fragment = new WeekViewBase();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.main_frame, fragment, "week");
            transaction.commit();

        } else if (id == R.id.nav_month) {
            Fragment fragment = new MonthView();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.main_frame, fragment, "month");
            transaction.commit();

        } else if (id == R.id.nav_taskboard) {
            Fragment fragment = new TaskBoard();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.main_frame, fragment, "taskboard");
            transaction.commit();

        } else if (id == R.id.nav_settings) {
            Fragment fragment = new SettingsView();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.main_frame, fragment, "settings");
            transaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://nudelsquad.nudelcalendar/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
        mainFrame = (FrameLayout) findViewById(R.id.main_frame);
        Fragment fragment = new StartScreenFrame();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.main_frame, fragment);
        transaction.commit();
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://nudelsquad.nudelcalendar/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }


    //Startscreen Fragment ist hier, weils sonst Probleme mit dem ändern des MainFrames gibt (glaub ich)
    public class StartScreenFrame extends Fragment implements View.OnClickListener {
        View rootView;

        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            //setContentView(R.layout.activity_day_list);
            rootView = inflater.inflate(
                    R.layout.startscreen_fragment, container, false);
            getUiInitialization();
            return rootView;
        }

        public void getUiInitialization() {
            Button btn = (Button) rootView.findViewById(R.id.btn_day);
            btn.setOnClickListener(this);
            btn = (Button) rootView.findViewById(R.id.btn_week);
            btn.setOnClickListener(this);
            btn = (Button) rootView.findViewById(R.id.btn_month);
            btn.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_day:
                    Fragment fragment = new DayList();
                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction transaction = fm.beginTransaction();
                    transaction.replace(R.id.main_frame, fragment);
                    transaction.commit();
                    break;
                case R.id.btn_week:
                    fragment = new WeekViewBase();
                    fm = getSupportFragmentManager();
                    transaction = fm.beginTransaction();
                    transaction.replace(R.id.main_frame, fragment);
                    transaction.commit();
                    break;
                case R.id.btn_month:
		    fragment = new WeekViewBase();
                    fragment = new MonthView();
                    fm = getSupportFragmentManager();
                    transaction = fm.beginTransaction();
                    transaction.replace(R.id.main_frame, fragment);
                    transaction.commit();
                    break;

            }
        }

    }

    private void setup() {

        br = new BroadcastReceiver() {
            @Override
            public void onReceive(Context c, Intent i) {

                //Get Settings
                if(!sharedPrefs.getBoolean(Pref_KEY_REMINDER, true))
                    return;

                int id = i.getExtras().getInt("id");
                final Event event = dbHandler.getEvent(id);


                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                // use System.currentTimeMillis() to have a unique ID for the pending intent
                PendingIntent pIntent = PendingIntent.getActivity(getBaseContext(), (int) System.currentTimeMillis(), intent, 0);

                // build notification
                // the addAction re-use the same intent to keep the example short
                Notification n = new Notification.Builder(getBaseContext())
                        .setContentTitle(event.getEVENT_NAME())
                        .setContentText(event.getEVENT_LOCATION())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentIntent(pIntent)
                        .setAutoCancel(true).build();


                NotificationManager notificationManager =
                        (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                notificationManager.notify(0, n);

                String audiopath = event.getEVENT_AUDIOPATH();


                t1 = new TextToSpeech(getBaseContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {

                        if (status == TextToSpeech.SUCCESS) {

                            int result = t1.setLanguage(getResources().getConfiguration().locale);

                            if (result == TextToSpeech.LANG_MISSING_DATA
                                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                                Log.e("TTS", "This Language is not supported");
                            } else {
                                t1.speak(event.getEVENT_NAME(), TextToSpeech.QUEUE_FLUSH, null);
                            }

                        } else {
                            Log.e("TTS", "Initilization Failed!");
                        }

                    }

                });


                Log.d("MAIN", "received: " + event.toString());
            }
        };


        registerReceiver(br, new IntentFilter("com.nudelsquad.Nudelcalendar"));

        am = (AlarmManager) (this.getSystemService(Context.ALARM_SERVICE));
    }
}
