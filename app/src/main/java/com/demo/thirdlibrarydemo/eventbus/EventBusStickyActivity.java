package com.demo.thirdlibrarydemo.eventbus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.demo.thirdlibrarydemo.R;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

public class EventBusStickyActivity extends AppCompatActivity {

    private Button get;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus_sticky);

        get= (Button) findViewById(R.id.get);
        text= (TextView) findViewById(R.id.text);

        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().register(EventBusStickyActivity.this);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MainThread,sticky = true)
    public void stickyMessage(EventBean eventBean){
        text.setText(eventBean.getMsg());
    }

}
