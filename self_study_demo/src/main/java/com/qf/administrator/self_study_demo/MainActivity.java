package com.qf.administrator.self_study_demo;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private PullToRefreshListView refreshListView;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        intiView();
        //设置label
        ILoadingLayout proxy = refreshListView.getLoadingLayoutProxy();
        //设置刷新提示
        proxy.setPullLabel("下拉以便刷新");
        proxy.setReleaseLabel("松开以便刷新");
        proxy.setRefreshingLabel("玩命加载中");
        //设置icon
        proxy.setLoadingDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
        //设置上次刷新的时间,Android中用DateUtils
        String dateTime = DateUtils.formatDateTime(this, System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR);
        proxy.setLastUpdatedLabel(dateTime);
    }

    private void initData(){
        List<String> list = new ArrayList<>();
        for(int i = 0; i < 20; i++){
            list.add("微信消息" + i);
        }
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
    }

    private void intiView(){
        refreshListView = (PullToRefreshListView) findViewById(R.id.listView);
        refreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        //绑定数据源
        refreshListView.setAdapter(adapter);
        //设置监听
        refreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>(){
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView){
                Toast.makeText(MainActivity.this, "下拉刷新", Toast.LENGTH_SHORT).show();
                //关闭
                sleep();

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView){
                Toast.makeText(MainActivity.this, "上拉刷新", Toast.LENGTH_SHORT).show();
                sleep();
            }
        });
    }

    private void sleep(){
        new Thread(){
            @Override
            public void run(){
                super.run();
                SystemClock.sleep(2000);
                runOnUiThread(new Runnable(){
                    @Override
                    public void run(){
                        refreshListView.onRefreshComplete();
                    }
                });
            }
        }.start();
    }
}
