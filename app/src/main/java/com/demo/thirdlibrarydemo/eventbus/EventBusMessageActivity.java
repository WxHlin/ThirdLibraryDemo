package com.demo.thirdlibrarydemo.eventbus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.demo.thirdlibrarydemo.R;

import de.greenrobot.event.EventBus;

public class EventBusMessageActivity extends AppCompatActivity {

    private TextView get_tv;
    private Button get,getSt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus_message);

        get_tv= (TextView) findViewById(R.id.get_tv);
        getSt= (Button) findViewById(R.id.getSt);
        get= (Button) findViewById(R.id.get);

        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new EventBean("Hello,妹纸！！！！！！"));
                finish();
            }
        });

        getSt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().postSticky(new EventBean("Hello,妹纸！！！！！！！！！！"));
                startActivity(new Intent(EventBusMessageActivity.this,EventBusStickyActivity.class));
            }
        });
    }

}
