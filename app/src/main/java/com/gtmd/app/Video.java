package com.gtmd.app;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.DiffCallback;
import java.util.List;

/**
 * Create time: 2017/12/4.
 */

public class Video {

  private List<ResultsBean> results;

  public List<ResultsBean> getResults() {
    return results;
  }

  public void setResults(List<ResultsBean> results) {
    this.results = results;
  }

  public static class ResultsBean {

    private String createdAt;
    private String id;
    private String img;
    private String objectId;
    private String title;
    private String updatedAt;
    private String url;

    public String getCreatedAt() {
      return createdAt;
    }

    public void setCreatedAt(String createdAt) {
      this.createdAt = createdAt;
    }

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getImg() {
      return img;
    }

    public void setImg(String img) {
      this.img = img;
    }

    public String getObjectId() {
      return objectId;
    }

    public void setObjectId(String objectId) {
      this.objectId = objectId;
    }

    public String getTitle() {
      return title;
    }

    public void setTitle(String title) {
      this.title = title;
    }

    public String getUpdatedAt() {
      return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
      this.updatedAt = updatedAt;
    }

    public String getUrl() {
      return url;
    }

    public void setUrl(String url) {
      this.url = url;
    }

    public static DiffCallback<ResultsBean> DIFF_CALLBACK = new DiffCallback<ResultsBean>() {
      @Override
      public boolean areItemsTheSame(@NonNull ResultsBean oldItem, @NonNull ResultsBean newItem) {
        return oldItem.objectId.equals(newItem.objectId);
      }

      @Override
      public boolean areContentsTheSame(@NonNull ResultsBean oldItem, @NonNull ResultsBean newItem) {
        return oldItem.equals(newItem);
      }
    };

    @Override
    public boolean equals(Object obj) {
      if (obj == null || !(obj instanceof ResultsBean)) {
        return false;
      }
      if (obj == this) {
        return true;
      }

      ResultsBean user = (ResultsBean) obj;

      return user.objectId.equals(this.objectId);
    }
  }
}
