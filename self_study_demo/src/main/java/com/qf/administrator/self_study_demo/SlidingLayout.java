package com.qf.administrator.self_study_demo;

import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SlidingLayout extends AppCompatActivity implements LeftFragment.FragCallBack{

    private FragmentManager manager;
    private RightFragment rightFragment;
    private LeftFragment leftfrag;
    private SlidingPaneLayout slidingPaneLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_layout);
        initView();
    }

    private void initView(){
        //使用兼容包下的
        manager = getSupportFragmentManager();
        slidingPaneLayout = (SlidingPaneLayout) findViewById(R.id.sliding);
        //动态创建Fragment
        leftfrag = LeftFragment.newInstance("left");
        manager.beginTransaction().add(R.id.frame1,leftfrag,"left").commit();
        rightFragment = new RightFragment();
        manager.beginTransaction().add(R.id.frame2,rightFragment,"right").commit();

    }

    @Override
    public void callBack(String url){
        rightFragment.openUrl(url);
        slidingPaneLayout.closePane();
    }
}
