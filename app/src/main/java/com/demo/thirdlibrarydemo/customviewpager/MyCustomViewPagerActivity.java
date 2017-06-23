package com.demo.thirdlibrarydemo.customviewpager;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.demo.thirdlibrarydemo.R;

public class MyCustomViewPagerActivity extends AppCompatActivity {

    private MyCustomViewPager viewPager;
    private RadioGroup group;

    private int[] ids={R.mipmap.a,R.mipmap.b,R.mipmap.c,R.mipmap.d,R.mipmap.e,R.mipmap.f};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_viewpager);

        viewPager= (MyCustomViewPager) findViewById(R.id.myviewpager);
        group= (RadioGroup) findViewById(R.id.group);

        for (int i = 0; i <ids.length ; i++) {
            ImageView imageView=new ImageView(this);
            imageView.setBackgroundResource(ids[i]);

            //添加到MyCustomViewPager这个viewgroup中
            viewPager.addView(imageView);
        }

        for (int i = 0; i <viewPager.getChildCount() ; i++) {
            RadioButton rb=new RadioButton(this);
            rb.setId(i);
            if (i==0){
                rb.setChecked(true);
            }
            group.addView(rb);
        }

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            /**
             *
             * @param group
             * @param checkedId   0-5之间
             */
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                viewPager.scrollToViewPager(checkedId);
            }
        });
        //设置页面监听的改变
        viewPager.setOnPageChangeListener(new MyCustomViewPager.onPageChangeListener() {
            @Override
            public void onScrollToPage(int position) {
                group.check(position);
            }
        });
    }


}
