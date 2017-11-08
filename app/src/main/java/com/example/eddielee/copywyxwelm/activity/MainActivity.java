package com.example.eddielee.copywyxwelm.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.eddielee.copywyxwelm.Databases;
import com.example.eddielee.copywyxwelm.FirstViewFragment.FirstMainFragment;
import com.example.eddielee.copywyxwelm.FirstViewFragment.FirstSecFragment;
import com.example.eddielee.copywyxwelm.R;
import com.example.eddielee.copywyxwelm.SecViewPager.SecViewPagerB;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener,SearchView.OnQueryTextListener{

    private static final int FLING_MIN_DISTANCE = 50;   //最小距离
    private static final int FLING_MIN_VELOCITY = 0;

    private SearchView sv;

    private GestureDetector mGestureDetector;

    private FirstMainFragment firstMainFragment;
    private FirstSecFragment firstSecFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.app_name,R.string.app_name);
        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);

        GestureDetector.SimpleOnGestureListener listener = new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                Log.e("<--滑动测试-->", "开始滑动");
                float x = e1.getX()-e2.getX();
                float x2 = e2.getX()-e1.getX();
                if(x>FLING_MIN_DISTANCE&&Math.abs(velocityX)>FLING_MIN_VELOCITY){

                    Toast.makeText(MainActivity.this, "向左手势", Toast.LENGTH_SHORT).show();
                }else if(x2>FLING_MIN_DISTANCE&&Math.abs(velocityX)>FLING_MIN_VELOCITY){
                    Toast.makeText(MainActivity.this, "向右手势", Toast.LENGTH_SHORT).show();
                }


                return super.onFling(e1, e2, velocityX, velocityY);
            }
        };

        mGestureDetector = new GestureDetector(this, listener);
        findViewById(R.id.fragment).setOnTouchListener(this);
        findViewById(R.id.fragment).setLongClickable(true);

        initView();
        initFragment(new SecViewPagerB());
    }

    private void initView() {
        findViewById(R.id.a).setOnClickListener(this);
        findViewById(R.id.b).setOnClickListener(this);
        findViewById(R.id.c).setOnClickListener(this);

        sv = (SearchView) findViewById(R.id.search_view);
        sv.setOnQueryTextListener(this);

        Databases databases = new Databases(this,"fff",null,1);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.a:
                initFragment(new FirstMainFragment());
                break;
            case R.id.b:
                initFragment(new FirstSecFragment());
                break;
            case R.id.c:
                Toast.makeText(this, "R.id.c", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(this, "look", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void initFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction().addToBackStack(null);
        transaction.replace(R.id.fragment,fragment);
        transaction.commit();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        FirstMainFragment firstMainFragment = FirstMainFragment.newInstance(query);
        initFragment(firstMainFragment);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }
}
