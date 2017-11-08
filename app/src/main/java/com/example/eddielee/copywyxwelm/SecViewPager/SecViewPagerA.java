package com.example.eddielee.copywyxwelm.SecViewPager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eddielee.copywyxwelm.PoJo.Menu;
import com.example.eddielee.copywyxwelm.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Eddie Lee on 10/17/2017.
 */

public class SecViewPagerA extends Fragment {

    private TextView title,ingredients,burden;
    private ImageView image_action;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sec_view_pager_a,container,false);

        Menu menu = (Menu) getArguments().getSerializable("menu");
        initView(view);

        title.setText(menu.getTitle());
        ingredients.setText(menu.getIngredients());
        burden.setText(menu.getBurden());
        ImageDrawable(menu);

        return view;
    }

    private void ImageDrawable(final Menu menu) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(menu.getAlbums());
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    InputStream is = connection.getInputStream();
                    final Bitmap bitmap = BitmapFactory.decodeStream(is);

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            image_action.setImageBitmap(bitmap);
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void initView(View view) {
        title = view.findViewById(R.id.text);
        ingredients = view.findViewById(R.id.ingredients);
        burden = view.findViewById(R.id.burden);
        image_action = view.findViewById(R.id.image_action);
    }

    public static  SecViewPagerA newInstance(Menu menu){
        SecViewPagerA fragmentOne = new SecViewPagerA();
        Bundle bundle = new Bundle();
        bundle.putSerializable("menu", menu);
        //fragment保存参数，传入一个Bundle对象
        fragmentOne.setArguments(bundle);
        return fragmentOne;
    }


}
