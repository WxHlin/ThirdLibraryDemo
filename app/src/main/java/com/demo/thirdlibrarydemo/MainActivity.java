package com.demo.thirdlibrarydemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.demo.thirdlibrarydemo.customview.CustomViewActivity;
import com.demo.thirdlibrarydemo.eventbus.EventBusActivity;
import com.demo.thirdlibrarydemo.recycleview.RecyclerViewActivity;

public class MainActivity extends Activity implements View.OnClickListener{


    private Button event_bus ,recyclerView,cus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        event_bus= (Button) findViewById(R.id.event_bus);
        recyclerView= (Button) findViewById(R.id.recyclerView);
        cus= (Button) findViewById(R.id.cus);

        event_bus.setOnClickListener(this);
        recyclerView.setOnClickListener(this);
        cus.setOnClickListener(this);
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
        }
    }
}
