package com.gtmd.app;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.DataSource;
import android.arch.paging.LivePagedListProvider;
import android.arch.paging.PagedList;
import com.gtmd.app.Video.ResultsBean;

/**
 * Create time: 2017/12/4.
 */

public class VideoViewModel extends ViewModel {
  private TDataSource tDataSource;
  private LiveData<PagedList<Video.ResultsBean>> videoList;
  private final MutableLiveData<ResultsBean> selected = new MutableLiveData<>();

  public VideoViewModel() {
  }

  public LiveData<PagedList<ResultsBean>> getVideoList() {
    return videoList;
  }

  public void setSelected(ResultsBean value) {
    selected.setValue(value);
  }

  public LiveData<ResultsBean> getSelected() {
    return selected;
  }

  public void init() {
    videoList = new LivePagedListProvider<Integer, Video.ResultsBean>() {
      @Override
      protected DataSource<Integer, ResultsBean> createDataSource() {
        tDataSource = new TDataSource();
        return tDataSource;
      }
    }.create(0,
            new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(20)
                .setInitialLoadSizeHint(20)
                .build());
  }
}
