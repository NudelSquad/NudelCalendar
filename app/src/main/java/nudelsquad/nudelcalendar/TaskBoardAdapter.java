package nudelsquad.nudelcalendar;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
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
            holder.grid_colortask = (LinearLayout) convertView.findViewById(R.id.task_color);
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_taskname);
            holder.tv_date = (TextView) convertView.findViewById(R.id.tv_duedate);
            holder.checktask = (CheckBox) convertView.findViewById(R.id.chk_task);
            holder.tv_description = (TextView) convertView.findViewById(R.id.txt_description) ;
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        Task taskitem =  task_list.get(position);

        holder.tv_name.setText(taskitem.getTASK_NAME());
        holder.tv_date.setText(taskitem.getTASK_DATUM());
        holder.checktask.setChecked(taskitem.getTASK_CHECKED());
        holder.grid_colortask.setBackgroundColor(taskitem.getTASK_COLOR());
        holder.tv_description.setText(taskitem.getTASK_TEXT());

        //TODO
        return convertView;
    }

    private class ViewHolder{
        LinearLayout grid_colortask;
        TextView tv_name, tv_date, tv_description;
        CheckBox checktask;
    }


}
