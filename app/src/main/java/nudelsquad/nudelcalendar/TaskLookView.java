package nudelsquad.nudelcalendar;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by Marco on 04.05.2016.
 */
public class TaskLookView extends Fragment {
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.tasklook_fragement, container, false);
        initGUI();

        return rootView;
    }

    public void initGUI(){
        FrameLayout color = (FrameLayout) rootView.findViewById(R.id.task_color);
        color.setBackgroundColor(Color.RED);
    }
}
