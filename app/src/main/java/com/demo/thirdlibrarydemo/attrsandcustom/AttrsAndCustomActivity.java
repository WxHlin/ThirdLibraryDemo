package com.demo.thirdlibrarydemo.attrsandcustom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.demo.thirdlibrarydemo.R;

public class AttrsAndCustomActivity extends AppCompatActivity {

    private TopBar topBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attrs_and_custom);

        topBar= (TopBar) findViewById(R.id.top);

        topBar.onTopBarClickListener(new TopBar.TopBarListener() {
            @Override
            public void onLeftClick() {
                Toast.makeText(AttrsAndCustomActivity.this,"点击左边",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightClick() {
                Toast.makeText(AttrsAndCustomActivity.this,"点击右边",Toast.LENGTH_SHORT).show();
            }
        });

        topBar.setleftIsVisiable(false);
    }
}
