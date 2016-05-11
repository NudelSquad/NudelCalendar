package nudelsquad.nudelcalendar;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by Marco on 04.05.2016.
 */
public class TaskLookView extends Fragment {
    private View rootView;
    private int taskID = -1;

    public TaskLookView(int taskID) {
        this.taskID = taskID;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.tasklook_fragement, container, false);
        initGUI();

        return rootView;
    }

    public void initGUI(){
        DBHandlerTask dbh = new DBHandlerTask(rootView.getContext());
        Task t = dbh.getTask(taskID);
        FrameLayout color = (FrameLayout) rootView.findViewById(R.id.task_color);
        color.setBackgroundColor(t.getTASK_COLOR());
        TextView tx = (TextView) rootView.findViewById(R.id.txt_task_name);
        tx.setText(t.getTASK_NAME());
        tx = (TextView) rootView.findViewById(R.id.txt_task_date);
        tx.setText(t.getTASK_DATUM());
        tx = (TextView) rootView.findViewById(R.id.txt_task_notes);
        tx.setText(t.getTASK_TEXT());
        CheckBox cb = (CheckBox) rootView.findViewById(R.id.cb_task_reminder);
        cb.setChecked(true);
    }
}
