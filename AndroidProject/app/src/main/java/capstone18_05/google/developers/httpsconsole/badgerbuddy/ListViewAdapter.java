package capstone18_05.google.developers.httpsconsole.badgerbuddy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListViewAdapter extends BaseAdapter {

    // Declare Variables

    Context mContext;
    LayoutInflater inflater;
    private List<BuddyNames> BuddyNamesList = null;
    private ArrayList<BuddyNames> arraylist;

    public ListViewAdapter(Context context, List<BuddyNames> BuddyNamesList) {
        mContext = context;
        this.BuddyNamesList = BuddyNamesList;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<BuddyNames>();
        this.arraylist.addAll(BuddyNamesList);
    }

    public class ViewHolder {
        TextView name;
    }

    @Override
    public int getCount() {
        return BuddyNamesList.size();
    }

    @Override
    public BuddyNames getItem(int position) {
        return BuddyNamesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.activity_list_view_item, null);
            // Locate the TextViews in activity_list_view_item.xml
            holder.name = (TextView) view.findViewById(R.id.name);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.name.setText(BuddyNamesList.get(position).getBuddyName());
        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        BuddyNamesList.clear();
        if (charText.length() == 0) {
            BuddyNamesList.addAll(arraylist);
        } else {
            for (BuddyNames wp : arraylist) {
                if (wp.getBuddyName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    BuddyNamesList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}
