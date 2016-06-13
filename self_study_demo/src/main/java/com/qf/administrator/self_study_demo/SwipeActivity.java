package com.qf.administrator.self_study_demo;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SwipeActivity extends AppCompatActivity{

    private ListView listView;
    private SwipeRefreshLayout refreshLayout;
    private ArrayAdapter<String> adapter;
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe);
        initData();
        initView();
        //设置颜色
        refreshLayout.setColorSchemeColors(Color.RED,Color.BLUE,Color.GREEN);
    }

    private void initData(){
        list = new ArrayList<>();
        for(int i = 0; i < 20; i++){
            list.add("微信消息" + i);
        }
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
    }

    private void initView(){
        listView = (ListView) findViewById(R.id.listView2);
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeLayout);
        listView.setAdapter(adapter);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh(){
                //模拟耗时操作
                new MyTask().execute();
            }
        });
    }

    class MyTask extends AsyncTask<Void, Void, String>{

        @Override
        protected String doInBackground(Void... params){
            for(int i = 0; i < 5; i++){
                list.add(0, "New 消息" + i);
                SystemClock.sleep(500);
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s){
            super.onPostExecute(s);
            adapter.notifyDataSetChanged();
            refreshLayout.setRefreshing(false);
        }
    }
}
