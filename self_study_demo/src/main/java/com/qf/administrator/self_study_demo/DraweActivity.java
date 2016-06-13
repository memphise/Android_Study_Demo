package com.qf.administrator.self_study_demo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class DraweActivity extends AppCompatActivity implements BitmapFragment.BitmapCallBack{

    private FrameLayout frameLayout;
    private FragmentManager manager;
    private ImageView imageView;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawe);
        initView();
    }

    private void initView(){
        frameLayout = (FrameLayout) findViewById(R.id.container);
        //动态创建一个ImageView
        imageView = new ImageView(this);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(params);
        frameLayout.addView(imageView);

      
        //动态添加Framgment
        manager = getSupportFragmentManager();
        BitmapFragment bitmapFragment = new BitmapFragment();
        manager.beginTransaction().add(R.id.drawer,bitmapFragment,"bitmap").commit();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
    }

    @Override
    public void callBack(Bitmap bitmap){

        imageView.setImageBitmap(bitmap);
        //关闭drawer
        drawerLayout.closeDrawers();
    }
}
