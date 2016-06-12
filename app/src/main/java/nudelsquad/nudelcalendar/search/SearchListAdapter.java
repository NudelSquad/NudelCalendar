package nudelsquad.nudelcalendar.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

import nudelsquad.nudelcalendar.R;

/**
 * Created by waser2 on 01.06.2016.
 */
public class SearchListAdapter extends BaseAdapter implements Filterable {

    private ArrayList<SearchItem> search_item_list;
    private ArrayList<SearchItem> orig;

    private Context layout_context_;
    LayoutInflater inflater;

    public SearchListAdapter(Context context,  ArrayList<SearchItem> objects) {
       super();
        this.layout_context_ = context;
        this.search_item_list = objects;
    }



    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults oReturn = new FilterResults();
                final ArrayList<SearchItem> results = new ArrayList<SearchItem>();
                if (orig == null)
                    orig = search_item_list;
                if (constraint != null) {
                    if (orig != null && orig.size() > 0) {
                        for (final SearchItem g : orig) {
                            if (g.getName_().toLowerCase().contains(constraint.toString()) ||
                                    g.getDate_().contains(constraint.toString()))
                                results.add(g);
                        }
                    }
                    oReturn.values = results;
                }
                return oReturn;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,
                                          FilterResults results) {
                search_item_list = (ArrayList<SearchItem>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public int getCount() {
        return search_item_list.size();
    }

    @Override
    public Object getItem(int position) {
        return search_item_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if(convertView==null)
        {
            convertView=LayoutInflater.from(layout_context_).inflate(R.layout.search_item, parent, false);
            holder=new ViewHolder();
            holder.searchname=(TextView) convertView.findViewById(R.id.tv_searchname);
            holder.searchdate=(TextView) convertView.findViewById(R.id.tv_searchdate);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        SearchItem searchItem =  search_item_list.get(position);

        //Log.e("EVENT", "StartTime = " + ev_bean_.getEVENT_START() + "Type = " + ev_bean_.getEVENT_TYPE());

        holder.searchname.setText(searchItem.getName_());
        holder.searchdate.setText(searchItem.getDate_());


        return convertView;
    }

    private class ViewHolder{
        TextView searchname;
        TextView searchdate;
    }




}
