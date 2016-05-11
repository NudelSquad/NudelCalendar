package nudelsquad.nudelcalendar;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.TextView;

import java.util.List;

public class DayListAdapter extends BaseAdapter {

    private List<Event> event_bean_list_;
    private Context layout_context_;
    LayoutInflater inflater;

    public DayListAdapter(Context context,  List<Event> objects) {
        //super(context, resource, objects);
        this.event_bean_list_ = objects;
        this.layout_context_ = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return event_bean_list_.size();
    }

    @Override
    public Object getItem(int position) {
        return event_bean_list_.get(position);
    }

    @Override
    public long getItemId(int position) {
        return event_bean_list_.get(position).getEVENT_ID();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if(convertView==null)
        {
            holder = new ViewHolder();
            convertView = this.inflater.inflate(R.layout.event_item,parent,false);
            holder.txtstart = (TextView) convertView.findViewById(R.id.tx_start);
            holder.txtend = (TextView) convertView.findViewById(R.id.tx_end);
            holder.txtevent = (TextView) convertView.findViewById(R.id.txt_eventname);
            holder.txttype = (TextView) convertView.findViewById(R.id.eventtype);
            holder.txtplace = (TextView) convertView.findViewById(R.id.eventplace);
            holder.gridColor = (GridLayout) convertView.findViewById(R.id.grid_color);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        Event ev_bean_ =  event_bean_list_.get(position);

        Log.e("EVENT", "StartTime = " + ev_bean_.getEVENT_START() + "Type = " + ev_bean_.getEVENT_TYPE());

        holder.txtstart.setText(ev_bean_.getEVENT_START());
        holder.txtend.setText(ev_bean_.getEVENT_END());
        holder.txtevent.setText(ev_bean_.getEVENT_NAME());
        holder.txttype.setText(ev_bean_.getEVENT_TYPE());
        holder.txtplace.setText(ev_bean_.getEVENT_LOCATION());
        holder.gridColor.setBackgroundColor(ev_bean_.getEVENT_COLOR());


        return convertView;
    }

    private class ViewHolder{
        TextView txtstart, txtend, txtevent, txttype, txtplace;
        GridLayout gridColor;

    }


}
