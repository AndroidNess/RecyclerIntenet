package tomerbu.itworks.edu.recyclerintenet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import tomerbu.itworks.edu.recyclerintenet.models.Child;

/**
 * Created by Dev on 16/02/2016.
 */
public class RedditAdapter extends BaseAdapter {
    private final List<Child> data;
    private final LayoutInflater inflater;
    private final Context context;

    //Constructor
    public RedditAdapter(List<Child> data,Context context) {
        this.data = data;
        this.context = context;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Only inflate when necessary
        if (convertView == null)
          convertView = inflater.inflate(R.layout.reddit_list_item, parent, false);

        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
        ImageView ivThumbnail = (ImageView) convertView.findViewById(R.id.ivThumbnail);

        Child reddit = data.get(position);

        tvTitle.setText(reddit.getData().getTitle());
        Picasso.with(context).
                load(reddit.getData().getThumbnail()).
                error(R.drawable.ic_error).
                placeholder(R.drawable.ic_placeholder).
                into(ivThumbnail);
        return convertView;
    }
}
