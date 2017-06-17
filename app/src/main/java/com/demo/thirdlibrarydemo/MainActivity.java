package com.demo.thirdlibrarydemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.demo.thirdlibrarydemo.eventbus.EventBusActivity;

public class MainActivity extends Activity implements View.OnClickListener{


    private Button event_bus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        event_bus= (Button) findViewById(R.id.event_bus);

        event_bus.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.event_bus:
                startActivity(new Intent(MainActivity.this, EventBusActivity.class));
                break;
        }
    }
}
