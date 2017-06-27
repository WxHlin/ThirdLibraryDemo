package com.demo.thirdlibrarydemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.demo.thirdlibrarydemo.asynctask.AsyncTaskActivity;
import com.demo.thirdlibrarydemo.attrsandcustom.AttrsAndCustomActivity;
import com.demo.thirdlibrarydemo.banner.BannerActivity;
import com.demo.thirdlibrarydemo.customview.CustomViewActivity;
import com.demo.thirdlibrarydemo.customviewpager.MyCustomViewPagerActivity;
import com.demo.thirdlibrarydemo.eventbus.EventBusActivity;
import com.demo.thirdlibrarydemo.premission.PremissionActivity;
import com.demo.thirdlibrarydemo.recycleview.RecyclerViewActivity;

public class MainActivity extends Activity implements View.OnClickListener{

    private Button event_bus ,recyclerView,cus,premission,banner,async_task
            ,cus_viewpager,attrs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        event_bus= (Button) findViewById(R.id.event_bus);
        recyclerView= (Button) findViewById(R.id.recyclerView);
        cus= (Button) findViewById(R.id.cus);
        cus_viewpager= (Button) findViewById(R.id.cus_viewpager);
        premission= (Button) findViewById(R.id.premission);
        banner= (Button) findViewById(R.id.banner);
        async_task= (Button) findViewById(R.id.async_task);
        attrs= (Button) findViewById(R.id.attrs);

        event_bus.setOnClickListener(this);
        recyclerView.setOnClickListener(this);
        cus.setOnClickListener(this);
        cus_viewpager.setOnClickListener(this);
        premission.setOnClickListener(this);
        banner.setOnClickListener(this);
        async_task.setOnClickListener(this);
        attrs.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.event_bus:
                startActivity(new Intent(MainActivity.this, EventBusActivity.class));
                break;
            case R.id.recyclerView:
                startActivity(new Intent(MainActivity.this, RecyclerViewActivity.class));
                break;
            case R.id.cus:
                startActivity(new Intent(MainActivity.this, CustomViewActivity.class));
                break;
            case R.id.cus_viewpager:
                startActivity(new Intent(MainActivity.this, MyCustomViewPagerActivity.class));
                break;
            case R.id.premission:
                startActivity(new Intent(MainActivity.this, PremissionActivity.class));
                break;
            case R.id.banner:
                startActivity(new Intent(MainActivity.this, BannerActivity.class));
                break;
            case R.id.async_task:
                startActivity(new Intent(MainActivity.this, AsyncTaskActivity.class));
                break;
            case R.id.attrs:
                startActivity(new Intent(MainActivity.this, AttrsAndCustomActivity.class));
                break;
        }
    }
}
