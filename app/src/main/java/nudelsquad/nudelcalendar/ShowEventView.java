package nudelsquad.nudelcalendar;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Marco on 04.05.2016.
 */
public class ShowEventView extends Fragment implements View.OnClickListener {
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.showevent_fragment, container, false);
        initGUI();
        return rootView;
    }

    public void initGUI(){
        ListView ls_tasks = (ListView) rootView.findViewById(R.id.list_event_tasks);
        String items[] = {"Task1", "Task2", "Task3"};
        ArrayAdapter adp = new ArrayAdapter(rootView.getContext(), android.R.layout.simple_list_item_1, items);
        ls_tasks.setAdapter(adp);
        ls_tasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.main_frame, new TaskLookView(), "NewFragmentTag");
                ft.commit();
            }
        });
        Button btn = (Button) rootView.findViewById(R.id.btn_event_delete);
        btn.setOnClickListener(this);
        btn = (Button) rootView.findViewById(R.id.btn_event_edit);
        btn.setOnClickListener(this);
        FrameLayout color = (FrameLayout) rootView.findViewById(R.id.event_color);
        color.setBackgroundColor(Color.RED);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_event_edit) {
            Toast.makeText(rootView.getContext(), "Clicked on Edit", Toast.LENGTH_SHORT).show();
        }
        else if (v.getId() == R.id.btn_event_delete){
            Toast.makeText(rootView.getContext(), "Clicked on Delete", Toast.LENGTH_SHORT).show();
        }
    }
}
