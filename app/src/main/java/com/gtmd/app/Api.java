package com.gtmd.app;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Create time: 2017/12/4.
 */

public class Api {
  public static ApiService createBmobApi() {
    OkHttpClient okHttpClient = new OkHttpClient.Builder()
        .addInterceptor(new Interceptor() {
          @Override
          public Response intercept(Interceptor.Chain chain) throws IOException {
            Request original = chain.request();

            Request.Builder requestBuilder = original.newBuilder()
                .header("X-Bmob-Application-Id", "699c928cd4de6ffed47532e4a22028f6")
                .header("X-Bmob-REST-API-Key", "e141f0c4afa40be6aa0cd0954fb29205")
                .header("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1");

            Request request = requestBuilder.build();
            return chain.proceed(request);
          }
        })
        .build();
    Retrofit.Builder builder = new Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://api.bmob.cn");

    return builder.build().create(ApiService.class);
  }
}
