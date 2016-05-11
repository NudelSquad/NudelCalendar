package nudelsquad.nudelcalendar;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
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
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FrameLayout mainFrame;      //Main Frame to switch between frames

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //OPEN ADD FRAME
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add_btn);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("hallo", "ja da");
/*
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("What do you want to create?")
                        .setItems(new String[]{"Event", "Task"}, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                if (which == 1){
                                    Fragment fragment = new CreateEventView();
                                    FragmentManager fm = getSupportFragmentManager();
                                    FragmentTransaction transaction = fm.beginTransaction();
                                    transaction.replace(R.id.main_frame, fragment);
                                    transaction.commit();
                                }
                            }
                        });
                builder.create();
*/
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("What do you want to create?");
                builder.setItems(new String[]{"Event", "Task"}, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Fragment fragment = null;
                        if (which == 0){
                            fragment = new CreateEventView();
                        }
                        if (which == 1){
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
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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
            return true;
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
            transaction.replace(R.id.main_frame, fragment);
            transaction.commit();

        } else if (id == R.id.nav_day) {
            Fragment fragment = new DayList();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.main_frame, fragment);
            transaction.commit();

        } else if (id == R.id.nav_week) {
            Fragment fragment = new WeekViewBase();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.main_frame, fragment);
            transaction.commit();

        } else if (id == R.id.nav_month) {
            Fragment fragment = new Speech();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.main_frame, fragment);
            transaction.commit();

        }
        else if (id == R.id.nav_taskboard)
        {
            Fragment fragment = new TaskBoard();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.main_frame, fragment);
            transaction.commit();

        } else if (id == R.id.nav_settings) {
            Fragment fragment = new SettingsView();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.main_frame, fragment);
            transaction.commit();
        }
        else if (id == R.id.nav_settings)
        {

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
        mainFrame = (FrameLayout)findViewById(R.id.main_frame);
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
    public class StartScreenFrame extends Fragment implements View.OnClickListener{
        View rootView;
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            //setContentView(R.layout.activity_day_list);
            rootView = inflater.inflate(
                    R.layout.startscreen_fragment, container, false);
            getUiInitialization();
            return rootView;
        }

        public void getUiInitialization(){
            Button btn = (Button)rootView.findViewById(R.id.btn_day);
            btn.setOnClickListener(this);
            btn = (Button)rootView.findViewById(R.id.btn_week);
            btn.setOnClickListener(this);
            btn = (Button)rootView.findViewById(R.id.btn_month);
            btn.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            switch (v.getId()){
                case R.id.btn_day:
                    Fragment fragment = new DayList();
                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction transaction = fm.beginTransaction();
                    transaction.replace(R.id.main_frame, fragment);
                    transaction.commit();
                    break;
                case  R.id.btn_week:
                    fragment = new WeekViewBase();
                    fm = getSupportFragmentManager();
                    transaction = fm.beginTransaction();
                    transaction.replace(R.id.main_frame, fragment);
                    transaction.commit();
                    break;
                case R.id.btn_month:fragment = new WeekViewBase();
                    fragment = new MonthView();
                    fm = getSupportFragmentManager();
                    transaction = fm.beginTransaction();
                    transaction.replace(R.id.main_frame, fragment);
                    transaction.commit();
                    break;
            }
        }
    }
}
