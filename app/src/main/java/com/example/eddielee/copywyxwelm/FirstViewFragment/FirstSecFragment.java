package com.example.eddielee.copywyxwelm.FirstViewFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eddielee.copywyxwelm.R;

/**
 * Created by Eddie Lee on 10/16/2017.
 */

public class FirstSecFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_sec_fragment,container,false);

        return view;
    }
}
