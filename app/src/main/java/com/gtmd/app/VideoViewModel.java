package com.gtmd.app;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.DataSource;
import android.arch.paging.LivePagedListProvider;
import android.arch.paging.PagedList;
import com.gtmd.app.Video.ResultsBean;

/**
 * Create time: 2017/12/4.
 */

public class VideoViewModel extends ViewModel {
  TDataSource tDataSource;
  public LiveData<PagedList<Video.ResultsBean>> videoList;

  public VideoViewModel() {
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
