package tomerbu.itworks.edu.recyclerintenet;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import tomerbu.itworks.edu.recyclerintenet.models.Child;

/**
 * Created by Dev on 16/02/2016.
 */
public class RedditRecyclerAdapter extends RecyclerView.Adapter<RedditViewHolder>{

    private final LayoutInflater inflater;
    private final List<Child> data;
    private final Context context;

    //Constructor
    public RedditRecyclerAdapter(List<Child> data, Context context) {
        this.data = data;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public RedditViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = inflater.inflate(R.layout.reddit_list_item, parent, false);
        return new RedditViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(RedditViewHolder holder, int position) {
        Child reddit = data.get(position);
        holder.tvTitle.setText(reddit.getData().getTitle());

        Picasso.with(context).load(reddit.getData().getThumbnail())
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_error)
                .into(holder.ivThumbnail);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
