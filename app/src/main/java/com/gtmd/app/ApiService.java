package com.gtmd.app;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Create time: 2017/12/4.
 */

public interface ApiService {
  @GET("/1/classes/video")
  Call<Video> getVideoList(@Query("skip") int skip, @Query("limit") int limit);
}
