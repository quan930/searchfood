package com.example.eddielee.copywyxwelm.SecViewPager;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import com.example.eddielee.copywyxwelm.R;

public class SecViewPagerB extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.sec_view_pager_b,container,false);

        VideoView videoView = view.findViewById(R.id.media_actions);
        videoView.setVideoURI(Uri.parse("android.resource://com.example.eddielee.copywyxwelm/" +R.raw.test));
        videoView.start();

        return view;
    }
}
