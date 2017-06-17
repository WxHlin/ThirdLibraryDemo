package com.demo.thirdlibrarydemo.eventbus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.demo.thirdlibrarydemo.R;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

public class EventBusActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView text;
    private Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);

        EventBus.getDefault().register(this);
        initView();
    }

    private void initView() {
        text= (TextView) findViewById(R.id.text);
        send= (Button) findViewById(R.id.sent);
        send.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sent://直接发送消息
                startActivity(new Intent(EventBusActivity.this,EventBusMessageActivity.class));
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void getMessage(EventBean eb){
        text.setText(eb.getMsg());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
