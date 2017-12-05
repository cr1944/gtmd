package com.gtmd.app;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.gtmd.app.Video.ResultsBean;

public class MainActivity extends AppCompatActivity {
  RecyclerView recyclerView;
  VideoListAdapter videoAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    recyclerView = findViewById(R.id.video_list);
    GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
    recyclerView.setLayoutManager(layoutManager);
    VideoViewModel viewModel = ViewModelProviders.of(this)
        .get(VideoViewModel.class);
    viewModel.init();
    videoAdapter = new VideoListAdapter(this);
    viewModel.getVideoList().observe(this, new Observer<PagedList<ResultsBean>>() {
      @Override
      public void onChanged(@Nullable PagedList<ResultsBean> resultsBeans) {
        videoAdapter.setList(resultsBeans);
      }
    });
    recyclerView.setAdapter(videoAdapter);
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
  }

  public void openVideo(ResultsBean selected) {
    VideoViewModel viewModel = ViewModelProviders.of(this)
        .get(VideoViewModel.class);
    viewModel.setSelected(selected);
    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    VideoFragment videoFragment = VideoFragment.newInstance();
    fragmentTransaction.replace(R.id.video_layout, videoFragment);
    fragmentTransaction.addToBackStack(null);
    fragmentTransaction.commit();
  }

  public void closeVideo() {
    FragmentManager fragmentManager = getSupportFragmentManager();
    Fragment fragment = fragmentManager.findFragmentById(R.id.video_layout);
    if (fragment != null) {
      FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
      fragmentTransaction.remove(fragment);
      fragmentTransaction.commit();
    }
  }
}
