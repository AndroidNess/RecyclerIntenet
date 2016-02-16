package tomerbu.itworks.edu.recyclerintenet;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Do the findViewByID once.
 */
public class RedditViewHolder extends RecyclerView.ViewHolder {
    TextView tvTitle;
    ImageView ivThumbnail;

    public RedditViewHolder(View convertView) {
        super(convertView);
        tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
        ivThumbnail = (ImageView) convertView.findViewById(R.id.ivThumbnail);

    }
}
