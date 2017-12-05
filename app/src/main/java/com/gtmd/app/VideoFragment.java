package com.gtmd.app;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.gtmd.app.Video.ResultsBean;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VideoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VideoFragment extends Fragment {
  private TextView textView;

  public VideoFragment() {
    // Required empty public constructor
  }

  public static VideoFragment newInstance() {
    VideoFragment fragment = new VideoFragment();
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    VideoViewModel viewModel = ViewModelProviders.of(getActivity())
        .get(VideoViewModel.class);
    viewModel.getSelected().observe(this, new Observer<ResultsBean>() {
      @Override
      public void onChanged(@Nullable ResultsBean resultsBean) {
        textView.setText(resultsBean.getTitle());
      }
    });
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_video, container, false);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    textView = view.findViewById(R.id.text);
  }
}
