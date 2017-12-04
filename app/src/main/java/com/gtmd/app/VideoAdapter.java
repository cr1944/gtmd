package com.gtmd.app;

import android.arch.paging.PagedListAdapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.gtmd.app.Video.ResultsBean;
import com.gtmd.app.VideoAdapter.VideoViewHolder;

/**
 * Create time: 2017/12/4.
 */

public class VideoAdapter extends PagedListAdapter<ResultsBean, VideoViewHolder> {

  protected VideoAdapter() {
    super(ResultsBean.DIFF_CALLBACK);
  }

  @Override
  public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
    View view = layoutInflater.inflate(R.layout.layout_video_item, parent, false);
    return new VideoViewHolder(view);
  }

  @Override
  public void onBindViewHolder(VideoViewHolder holder, int position) {
    ResultsBean bean = getItem(position);
    if (bean != null) {
      holder.bindTo(bean);
    }
  }

  public static class VideoViewHolder extends ViewHolder {
    TextView titleView;
    ImageView imageView;

    public VideoViewHolder(View itemView) {
      super(itemView);
      titleView = itemView.findViewById(R.id.video_title);
      imageView = itemView.findViewById(R.id.video_image);
    }

    public void bindTo(ResultsBean bean) {
      titleView.setText(bean.getTitle());
      Glide.with(itemView)
          .load(bean.getImg())
          .apply(new RequestOptions().placeholder(R.drawable.img_bg))
          .into(imageView);
    }
  }
}
