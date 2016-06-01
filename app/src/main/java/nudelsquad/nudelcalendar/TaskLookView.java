package nudelsquad.nudelcalendar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Marco on 04.05.2016.
 */
public class TaskLookView extends Fragment {
    private View rootView;
    private int taskID = -1;
    private DBHandler dbh;

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
        dbh = new DBHandler(rootView.getContext());
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

        Button delete = (Button) rootView.findViewById(R.id.btn_delete_task);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert1 = new AlertDialog.Builder(rootView.getContext());
                alert1.setMessage("Delete Task??!")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dbh.deleteTask(taskID);
                                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                                ft.replace(R.id.main_frame, new TaskBoard(), "NewFragmentTag");
                                ft.commit();
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert3 = alert1.create();
                alert3.setTitle("DELETE TASK");
                alert3.show();
            }
        });

        Button edit = (Button) rootView.findViewById(R.id.btn_edit_task);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(rootView.getContext(), "Clicked on Edit", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
