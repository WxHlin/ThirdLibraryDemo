package com.demo.thirdlibrarydemo.recycleview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.demo.thirdlibrarydemo.R;

import java.util.ArrayList;

public class RecyclerViewActivity extends AppCompatActivity implements View.OnClickListener{

    private Button add,delete,list,grid,flow;
    private RecyclerView recyclerView;

    private ArrayList<String> datas=new ArrayList<String>();
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        initView();
        for (int i = 0; i <100 ; i++) {
            datas.add("第"+i+"个");
        }

        //设置适配器
        adapter=new MyAdapter(RecyclerViewActivity.this,datas);
        recyclerView.setAdapter(adapter);
        //点击
        adapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, String data) {
                Toast.makeText(RecyclerViewActivity.this,data,Toast.LENGTH_SHORT);
            }
        });

        /**
         * 设置动画，可自定义
         */
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    private void initView() {
        recyclerView= (RecyclerView) findViewById(R.id.recyclerView);
        add= (Button) findViewById(R.id.add);
        delete= (Button) findViewById(R.id.delete);
        list= (Button) findViewById(R.id.list);
        grid= (Button) findViewById(R.id.grid);
        flow= (Button) findViewById(R.id.flow);
        add.setOnClickListener(this);
        delete.setOnClickListener(this);
        list.setOnClickListener(this);
        grid.setOnClickListener(this);
        flow.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.add:
                adapter.addData(0,"new data");
                recyclerView.scrollToPosition(0);//添加数据后，显示第一条数据
                break;
            case R.id.delete:
                adapter.removeData(0,"new data");
                break;
            case R.id.list:
                /**
                 *    new LinearLayoutManager(RecyclerViewActivity.this,LinearLayoutManager.VERTICAL,false)
                 * 参数：上下文
                 *       设置滑动方向
                 *       false表示是否倒叙
                 */
                recyclerView.setLayoutManager(new LinearLayoutManager(RecyclerViewActivity.this,LinearLayoutManager.VERTICAL,false));
                break;
            case R.id.grid:
                /**
                 *    new GridLayoutManager(RecyclerViewActivity.this,3,GridLayoutManager.VERTICAL,false)
                 *    参数：上下文
                 *          表示列数
                 *          方向
                 *          false表示是否倒叙
                 */
                recyclerView.setLayoutManager(new GridLayoutManager(RecyclerViewActivity.this,3,GridLayoutManager.VERTICAL,false));
                break;
            case R.id.flow:
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                break;
        }
    }
}
