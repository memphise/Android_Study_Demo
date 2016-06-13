package com.qf.administrator.pulltorefresh;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateUtils;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private List<String> list;
    private ArrayAdapter adapter;
    private PullToRefreshListView ptr;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
        ILoadingLayout proxy = ptr.getLoadingLayoutProxy();
        //更新标题
        proxy.setPullLabel("下拉以刷新");
        proxy.setRefreshingLabel("玩命加载中");
        proxy.setReleaseLabel("松开以刷新");
        //更新刷新时候的icon
        proxy.setLoadingDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
        //更新上次刷新时间
        String dateTime = DateUtils.formatDateTime(this, System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR);
        proxy.setLastUpdatedLabel(dateTime);
    }

    private void initData(){
        list = new ArrayList();
        for(int i = 0; i < 10; i++){
            list.add("qq消息" + i);
        }
    }

    private void initView(){
        ptr = (PullToRefreshListView) findViewById(R.id.pullToRefresh);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        ptr.setAdapter(adapter);
     /*   ptr.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>(){
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView){
                doAsyncTask(); //模拟耗时操作
            }
        });*/
        //设置可以双向拉动Mode
        ptr.setMode(PullToRefreshBase.Mode.BOTH);
        //设置双向拉的监听
        ptr.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>(){
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView){
                //向下拉的时候
                doAsyncTask();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView){
                //向上拉的时候
                doAsyncTask2();
            }
        });
    }

    private void doAsyncTask(){
        new Thread(){
            @Override
            public void run(){
                for(int i = 0; i < 5; i++){
                    list.add(0, "下拉刷新新的qq消息" + i);
                    //模拟耗时操作
                    SystemClock.sleep(1000);
                }
                updateUI(); //更新UI操作
            }
        }.start();
    }

    private void doAsyncTask2(){
        new Thread(){
            @Override
            public void run(){
                for(int i = 0; i < 5; i++){
                    list.add("上拉刷新新的qq消息" + i);
                    //模拟耗时操作
                    SystemClock.sleep(1000);
                }
                updateUI(); //更新UI操作
            }
        }.start();
    }

    private void updateUI(){
        ptr.post(new Runnable(){
            @Override
            public void run(){
                //更新UI
                adapter.notifyDataSetChanged();
                //关闭刷新
                ptr.onRefreshComplete();
            }
        });
    }
}
