package nudelsquad.nudelcalendar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

/**
 * Created by Marco on 04.05.2016.
 */
public class SettingsView extends Fragment implements View.OnClickListener{
    private View rootView;
    SharedPreferences sharedPrefs = null;                       //to Save Settings
    private static final String PrefName = "SettingPreferences";
    private static final String Pref_KEY_LANDSC = "LANDSCAPEMODE";
    private static final String Pref_KEY_REMINDER = "REMINDER";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.settings_fragment, container, false);
        sharedPrefs = rootView.getContext().getSharedPreferences(PrefName, 0);
        initGUI();
        return rootView;
    }

    public void initGUI(){
        Button btn_save = (Button) rootView.findViewById(R.id.btnSave);
        btn_save.setOnClickListener(this);
        btn_save = (Button) rootView.findViewById(R.id.btnDelEvents);
        btn_save.setOnClickListener(this);
        btn_save = (Button) rootView.findViewById(R.id.btnDelTasks);
        btn_save.setOnClickListener(this);
        if(sharedPrefs.getBoolean(Pref_KEY_LANDSC, false)){
            Switch landSC = (Switch) rootView.findViewById(R.id.swLS);
            landSC.setChecked(true);
        }
        if(sharedPrefs.getBoolean(Pref_KEY_REMINDER, true)){
            Switch landSC = (Switch) rootView.findViewById(R.id.swRem);
            landSC.setChecked(true);
        }
    }

    @Override
    public void onClick(View v) {
        DBHandler dbh = new DBHandler(rootView.getContext());
        if (v.getId() == R.id.btnSave){
            Toast.makeText(rootView.getContext(), R.string.saved, Toast.LENGTH_LONG).show();
            Switch landSC = (Switch) rootView.findViewById(R.id.swLS);
            Switch rem = (Switch) rootView.findViewById(R.id.swRem);
            SharedPreferences.Editor editor = sharedPrefs.edit();
            editor.putBoolean(Pref_KEY_LANDSC, landSC.isChecked());
            editor.putBoolean(Pref_KEY_REMINDER, rem.isChecked());
            editor.commit();
        }
        if (v.getId() == R.id.btnDelEvents){
            Toast.makeText(rootView.getContext(), "All Events deleted", Toast.LENGTH_LONG).show();
            dbh.deleteAllEvents();
        }
        if (v.getId() == R.id.btnDelTasks){
            Toast.makeText(rootView.getContext(), "All Tasks deleted", Toast.LENGTH_LONG).show();
            dbh.deleteAllTasks();
        }
        Intent intent = new Intent(rootView.getContext(), MainActivity.class);
        //intent.setFlag(Intent.CLEAR_TASK);
        startActivity(intent);
    }
}
