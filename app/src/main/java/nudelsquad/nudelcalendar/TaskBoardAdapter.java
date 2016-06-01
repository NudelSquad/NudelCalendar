package nudelsquad.nudelcalendar;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;

import java.util.Comparator;
import java.util.List;

public class TaskBoardAdapter extends BaseAdapter {

    private List<Task> task_list;
    private Context layout_context_;
    LayoutInflater inflater;
    private DBHandler dbh;
    public static final String TAG = "TASKBOARD";


    public TaskBoardAdapter(Context context, List<Task> objects) {
        //super(context, resource, objects);
        this.task_list = objects;
        this.layout_context_ = context;
        this.inflater = LayoutInflater.from(context);
        this.dbh = new DBHandler(context);
    }

    public void resetAndLoadList() {
        task_list=null;
        task_list=dbh.getAllTasks();
    }


    @Override
    public int getCount() {
        return task_list.size();
    }

    @Override
    public Object getItem(int position) {
        return task_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return task_list.get(position).getTASK_ID();
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = this.inflater.inflate(R.layout.task_item, parent, false);
            holder.grid_colortask = (LinearLayout) convertView.findViewById(R.id.task_color);
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_taskname);
            holder.tv_date = (TextView) convertView.findViewById(R.id.tv_duedate);
            holder.checktask = (CheckBox) convertView.findViewById(R.id.chk_task);
            holder.tv_description = (TextView) convertView.findViewById(R.id.txt_description);
            holder.position = position;
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        Task taskitem =  task_list.get(position);

        setSwipe(convertView, parent);



        holder.tv_name.setText(taskitem.getTASK_NAME());
        holder.tv_date.setText(taskitem.getTASK_DATUM());
        holder.checktask.setChecked(taskitem.getTASK_CHECKED());
        holder.grid_colortask.setBackgroundColor(taskitem.getTASK_COLOR());
        holder.tv_description.setText(taskitem.getTASK_TEXT());
        holder.position=position;
        holder.id=taskitem.getTASK_ID();

        CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.chk_task);
        checkBox.setTag(holder);

        Button button = (Button) convertView.findViewById(R.id.btn_delete_task);
        button.setTag(holder);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "delte");
                ViewHolder viewHolder= (ViewHolder) v.getTag();
                int id = viewHolder.id;
                dbh.deleteTask(id);
                swapItems();

            }
        });


        checkBox.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                ViewHolder viewHolder = (ViewHolder) v.getTag();

                Task task = task_list.get(viewHolder.position);

                if(task.getTASK_ID()!=viewHolder.id)
                    Toast.makeText(v.getContext(), viewHolder.id, Toast.LENGTH_SHORT);

                task.setTASK_CHECKED((((CheckBox) v).isChecked()));

                dbh.updateTask(task);
                Log.i(TAG, dbh.getTask(task.getTASK_ID()).toString());
                swapItems();
            }
        });

        //TODO
        return convertView;
    }

    public void setSwipe(View swipe, ViewGroup parent) {
        SwipeLayout swipeLayout = (SwipeLayout) swipe;
        swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);

        //add drag edge.(If the BottomView has 'layout_gravity' attribute, this line is unnecessary)
//        swipeLayout.addDrag(SwipeLayout.DragEdge.Left, swipeLayout.findViewById(R.id.bottom_wrapper));

        swipeLayout.addSwipeListener(new SwipeLayout.SwipeListener() {
            @Override
            public void onClose(SwipeLayout layout) {
                //when the SurfaceView totally cover the BottomView.
            }

            @Override
            public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {
                //you are swiping.
            }

            @Override
            public void onStartOpen(SwipeLayout layout) {

            }

            @Override
            public void onOpen(SwipeLayout layout) {
                //when the BottomView totally show.
            }

            @Override
            public void onStartClose(SwipeLayout layout) {

            }

            @Override
            public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {
                //when user's hand released.
            }
        });

    }

    public void swapItems() {
        task_list = dbh.getAllTasks();
        notifyDataSetChanged();
    }

    private class ViewHolder {
        LinearLayout grid_colortask;
        TextView tv_name, tv_date, tv_description;
        int position;
        CheckBox checktask;
        int id;
    }

//
//    static final Comparator<Task> TASK_COMPARATOR =
//            new Comparator<Task>() {
//                public int compare(Task t1, Task t2) {
//                    int dateCmp = t2.().compareTo(t1.hireDate());
//                    if (dateCmp != 0)
//                        return dateCmp;
//
//                    return (t1.number() < t2.number() ? -1 :
//                            (t1.number() == t2.number() ? 0 : 1));
//                }
//            };


}
