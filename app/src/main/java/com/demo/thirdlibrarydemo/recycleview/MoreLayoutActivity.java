package com.demo.thirdlibrarydemo.recycleview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.demo.thirdlibrarydemo.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/6/27 0027.
 */

public class MoreLayoutActivity extends AppCompatActivity{

    private RecyclerView recyclerView;
    private ArrayList<String> datas=new ArrayList<String>();
    private MoreLayoutAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_more);

        recyclerView= (RecyclerView) findViewById(R.id.recyclerView);
        for (int i = 0; i <100 ; i++) {
            datas.add("第"+i+"个");
        }

        //设置适配器
        adapter=new MoreLayoutAdapter(MoreLayoutActivity.this,datas);
        recyclerView.setAdapter(adapter);
        //点击    MyAdapter接口回调
        adapter.setOnItemClickListener(new MoreLayoutAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, String data) {
                Toast.makeText(MoreLayoutActivity.this,data,Toast.LENGTH_SHORT).show();
            }
        });

        //添加自定义分割线，大部分情况下载item最外层中添加margin
        recyclerView.addItemDecoration(new MyDecoration(MoreLayoutActivity.this));
        /**
         * 设置动画，可自定义
         */
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setLayoutManager(new LinearLayoutManager(MoreLayoutActivity.this,LinearLayoutManager.VERTICAL,false));
    }
}
