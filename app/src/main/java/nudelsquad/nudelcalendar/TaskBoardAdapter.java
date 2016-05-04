package nudelsquad.nudelcalendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

public class TaskBoardAdapter extends BaseAdapter {

    private List<Task> task_list;
    private Context layout_context_;
    LayoutInflater inflater;

    public TaskBoardAdapter(Context context, List<Task> objects) {
        //super(context, resource, objects);
        this.task_list = objects;
        this.layout_context_ = context;
        this.inflater = LayoutInflater.from(context);
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
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if(convertView==null)
        {
            holder = new ViewHolder();
            convertView = this.inflater.inflate(R.layout.task_item,parent,false);
            holder.txtname = (TextView) convertView.findViewById(R.id.txt_taskname);
            holder.txtdate = (TextView) convertView.findViewById(R.id.txt_duedate);
            holder.chk_task = (CheckBox) convertView.findViewById(R.id.check_task);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        Task taskitem =  task_list.get(position);


        holder.txtname.setText(taskitem.getTASK_NAME());
        holder.txtdate.setText(Integer.toString(taskitem.getTASK_DUE()));
        holder.chk_task.setChecked(taskitem.isTASK_DONE());

        return convertView;
    }

    private class ViewHolder{
        TextView txtname, txtdate;
        CheckBox chk_task;

    }


}
