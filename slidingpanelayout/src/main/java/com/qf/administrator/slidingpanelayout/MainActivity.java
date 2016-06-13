package com.qf.administrator.slidingpanelayout;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LeftFragment.UrlCallBack{

    private SlidingPaneLayout slidingPaneLayout;
    private RightFragment rightfrag;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        slidingPaneLayout = (SlidingPaneLayout) findViewById(R.id.slidingPane);
        //动态添加Fragment
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.left, new LeftFragment(), "leftfrag").commit();
        manager.beginTransaction().add(R.id.right, new RightFragment(), "rightfrag").commit();
        //给侧滑界面设置监听
        slidingPaneLayout.setPanelSlideListener(new SlidingPaneLayout.PanelSlideListener(){
            @Override
            public void onPanelSlide(View panel, float slideOffset){
            }

            @Override
            public void onPanelOpened(View panel){
                Toast.makeText(MainActivity.this, "SlidingPaneLayout拉开了", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPanelClosed(View panel){
                Toast.makeText(MainActivity.this, "SlidingPaneLayout关闭了", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void callBack(String url){
        //方法的具体实现
        if(rightfrag == null){
            rightfrag = (RightFragment) getSupportFragmentManager().findFragmentByTag("rightfrag");
        }
        rightfrag.viewWeb(url);
        slidingPaneLayout.closePane();
    }
}
