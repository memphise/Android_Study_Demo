package com.qf.administrator.drawerlayout;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private DrawerLayout drawerLayout;
    private String[] urls;
    private List<String> list;
    private ListView listView;
    private RightFragment rightFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initData(){
        list = new ArrayList<>();
        list.add("百度");
        list.add("腾讯");
        list.add("谷歌");
        list.add("360");
        urls = new String[]{"http://www.baidu.com", "http://www.qq.com", "http://www.google.com", "http://www.360.com"};
    }

    private void initView(){
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        listView = (ListView) findViewById(R.id.drawerView);
        rightFragment = new RightFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container, rightFragment, "right").commit();
        //绑定适配器
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                rightFragment.viewWeb(urls[position]);
//                drawerLayout.closeDrawer(Gravity.LEFT);
                drawerLayout.closeDrawers();
            }
        });

        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener(){
            @Override
            public void onDrawerClosed(View drawerView){
                super.onDrawerClosed(drawerView);
                Toast.makeText(MainActivity.this, "onDrawerClosed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerOpened(View drawerView){
                super.onDrawerOpened(drawerView);
                Toast.makeText(MainActivity.this, "onDrawerOpened", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
