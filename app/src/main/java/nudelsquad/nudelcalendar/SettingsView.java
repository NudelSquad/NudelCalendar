package nudelsquad.nudelcalendar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by Marco on 04.05.2016.
 */
public class SettingsView extends Fragment implements View.OnClickListener{
    private View rootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.settings_fragment, container, false);
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
        Spinner spin = (Spinner) rootView.findViewById(R.id.spMuteFor);
        String items[] = {"15", "30", "45", "60", "75"};
        ArrayAdapter adp = new ArrayAdapter(rootView.getContext(), R.layout.support_simple_spinner_dropdown_item, items);
        spin.setAdapter(adp);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnSave){
            Toast.makeText(rootView.getContext(), R.string.saved, Toast.LENGTH_LONG).show();
        }
    }
}
