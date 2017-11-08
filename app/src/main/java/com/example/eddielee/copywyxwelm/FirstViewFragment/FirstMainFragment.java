package com.example.eddielee.copywyxwelm.FirstViewFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.eddielee.copywyxwelm.PoJo.Menu;
import com.example.eddielee.copywyxwelm.R;
import com.example.eddielee.copywyxwelm.SecViewPager.SecViewPagerA;
import com.example.eddielee.copywyxwelm.SecViewPager.SecViewPagerB;
import com.example.eddielee.copywyxwelm.util.Connection;
import com.example.eddielee.copywyxwelm.util.FragAdapter;

import java.util.ArrayList;
import java.util.List;

public class FirstMainFragment extends Fragment {

    private ViewPager pager;
    private FragAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_main_fragment, container, false);
        initView(view);

        if (getArguments()!=null){
            getMenuFromWeb(getArguments().getString("menu"));
        }

        return view;
    }

    private void initView(View view) {
        pager = view.findViewById(R.id.view_pager);
    }

    public void getMenuFromWeb(final String menu) {
        try {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Connection connection = new Connection();
                        List<Menu> menus = connection.connectionTo(menu);

                        final List<Menu> finalMenus = menus;
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                int size = finalMenus.size();
                                List<Fragment> list = new ArrayList<Fragment>();
                                for (int i = 0;i<size;i++){
                                    list.add(SecViewPagerA.newInstance(finalMenus.get(i)));
                                }
                                /**
                                 * Must be getChildFragmentManager(),
                                 * getFragmentManager是为了获取能管理和当前Activity有关联的Fragment的FragmentManager。
                                 * getChildFragmentManager返回一个FragmentManager为了设置和管理当前Fragment内部的Fragment的们
                                 * **/
                                adapter = new FragAdapter(getChildFragmentManager(), list);
                                pager.setAdapter(adapter);
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static  FirstMainFragment newInstance(String menu){
        FirstMainFragment fragment = new FirstMainFragment();
        Bundle bundle = new Bundle();
        bundle.putString("menu", menu);
        //fragment保存参数，传入一个Bundle对象
        fragment.setArguments(bundle);
        return fragment;
    }
}
