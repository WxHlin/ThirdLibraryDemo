package com.demo.thirdlibrarydemo.attrsandcustom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.demo.thirdlibrarydemo.R;

/**
 * Created by Administrator on 2017/6/27 0027.
 */

public class TopBar extends RelativeLayout {
    //中间title
    private String title;
    private float titleTextSize;
    private int titleTextColor;
    //左边按钮
    private int leftBtnTextColor;
    private Drawable leftBtnBackground;
    private String leftBtnText;
    //右边按钮
    private int rightBtnTextColor;
    private Drawable rightBtnBackground;
    private String rightBtnText;

    private Button leftBtn,rightBtn;
    private TextView mTitle;

    private LayoutParams leftPl,rightPl,titlePl;

    public TopBar(Context context) {
        super(context);
    }

    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        //获取属性
        TypedArray ta=context.obtainStyledAttributes(attrs, R.styleable.TopBar);
        for (int i = 0; i < ta.getIndexCount(); i++) {
            int index=ta.getIndex(i);
            switch (index){
                case R.styleable.TopBar_leftBtnBackground:
                    leftBtnBackground=ta.getDrawable(index);
                    break;
                case R.styleable.TopBar_leftBtnText:
                    leftBtnText=ta.getString(index);
                    break;
                case R.styleable.TopBar_leftBtnTextColor:
                    leftBtnTextColor=ta.getColor(index,0);
                    break;
                case R.styleable.TopBar_rightBtnBackground:
                    rightBtnBackground=ta.getDrawable(index);
                    break;
                case R.styleable.TopBar_rightBtnText:
                    rightBtnText=ta.getString(index);
                    break;
                case R.styleable.TopBar_rightBtnTextColor:
                    rightBtnTextColor=ta.getColor(index,0);
                    break;
                case R.styleable.TopBar_titleText:
                    title=ta.getString(index);
                    break;
                case R.styleable.TopBar_titleTextColor:
                    titleTextColor=ta.getColor(index,0);
                    break;
                case R.styleable.TopBar_titleTextSize:
                    titleTextSize=ta.getDimension(index,0);
                    break;
            }
        }
        ta.recycle();

        leftBtn=new Button(context);
        rightBtn=new Button(context);
        mTitle=new TextView(context);

        leftBtn.setTextColor(leftBtnTextColor);
        leftBtn.setBackground(leftBtnBackground);
        leftBtn.setText(leftBtnText);

        rightBtn.setTextColor(rightBtnTextColor);
        rightBtn.setBackground(rightBtnBackground);
        rightBtn.setText(rightBtnText);

        mTitle.setText(title);
        mTitle.setTextColor(titleTextColor);
        mTitle.setTextSize(titleTextSize);
        mTitle.setGravity(Gravity.CENTER);//文字居中

        setBackgroundColor(0xFFF59563);//设置背景颜色
        //添加左边按钮
        leftPl=new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        leftPl.addRule(RelativeLayout.ALIGN_PARENT_LEFT,TRUE);
        addView(leftBtn,leftPl);
        //添加右边按钮
        rightPl=new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        rightPl.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,TRUE);
        addView(rightBtn,rightPl);
        //添加中间标题
        titlePl=new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);
        titlePl.addRule(RelativeLayout.CENTER_IN_PARENT,TRUE);
        addView(mTitle,titlePl);

        leftBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onLeftClick();
            }
        });

        rightBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRightClick();
            }
        });
    }

    //点击回调
    public TopBarListener listener;

    public interface TopBarListener{
        void onLeftClick();
        void onRightClick();
    }

    public void onTopBarClickListener(TopBarListener listener){
        this.listener=listener;
    }

    public void setleftIsVisiable(boolean flag){
        if (flag){
            leftBtn.setVisibility(View.VISIBLE);
        } else{
            leftBtn.setVisibility(View.GONE);
        }
    }
}
