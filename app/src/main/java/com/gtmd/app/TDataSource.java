package com.gtmd.app;

import android.arch.paging.DataSource;
import android.arch.paging.TiledDataSource;
import android.util.Log;
import com.gtmd.app.Video.ResultsBean;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Response;

/**
 * Create time: 2017/12/4.
 */

public class TDataSource extends TiledDataSource<Video.ResultsBean> {
  private static final String TAG = "TDataSource";
  private ApiService apiService;

  public TDataSource() {
    apiService = Api.createBmobApi();
  }

  @Override
  public int countItems() {
    return DataSource.COUNT_UNDEFINED;
  }

  @Override
  public List<ResultsBean> loadRange(int startPosition, int count) {
    List<ResultsBean> result = new ArrayList<>();
    try {
      Log.e("TDataSource", "loadRange: startPosition=" + startPosition + ", count=" + count);
      Response<Video> response = apiService.getVideoList(startPosition, count).execute();
      if (response.isSuccessful() && response.body() != null) {
        result.addAll(response.body().getResults());
      } else {
        Log.e(TAG, response.message());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return result;
  }
}
