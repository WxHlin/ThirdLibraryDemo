package com.demo.thirdlibrarydemo.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ToggleButton;

import com.demo.thirdlibrarydemo.R;

/**
 * Created by Administrator on 2017/6/22 0022.
 * 1、构造方法实例化类
 * 2、测量-->measure(int,int)-->onMeasure();
 *    如果当前View是一个ViewGroup,还有义务测量孩子
 *    孩子有建议权
 * 3、指定位置-layout()-->onLayout();
 *    指定控件的位置，一般View不用写这个方法，ViewGroup才需要
 * 4、绘制视图--draw()--->onDraw(canvas);
 */
public class MyToggleButton extends ToggleButton implements View.OnClickListener{

    private Bitmap backgroundBiamap;
    private Bitmap SlideBitmap;

    /**
     * 移动的最大距离
     */
    private int slideMax;
    private Paint paint;
    private int slideLeft;

    /**
     * 如果在布局文件使用该类，将会用这个构造方法实例该类，如果没有会崩溃
     * @param context
     * @param attrs
     */
    public MyToggleButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        
        initView();
    }

    private void initView() {
        paint=new Paint();
        paint.setAntiAlias(true);//设置抗锯齿
        backgroundBiamap= BitmapFactory.decodeResource(getResources(), R.mipmap.background);
        SlideBitmap=BitmapFactory.decodeResource(getResources(),R.mipmap.slide);
        slideMax=backgroundBiamap.getWidth()-SlideBitmap.getWidth();

        setOnClickListener(this);//点击事件
    }


    /**
     * 视图的测量
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);//调父类方法
        setMeasuredDimension(backgroundBiamap.getWidth(),backgroundBiamap.getHeight());
    }


    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        /**
         * canvas不能绘制资源文件，要先转换成bitmap
         */
        canvas.drawBitmap(backgroundBiamap,0,0,paint);
        canvas.drawBitmap(SlideBitmap,slideLeft,0,paint);
    }

    private boolean isOpend=false;//判断开关状态
    /**
     * true:点击事件生效，滑动事件不生效
     * false：点击事件不生效，互动事件生效
     */
    private boolean isEnableClick=true;

    /**
     * 点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        if (isEnableClick){
            isOpend=!isOpend;
            update();
        }
    }

    /**
     * 更新状态方法
     */
    private void update() {
        if (isOpend){
            slideLeft=slideMax;
        } else{
            slideLeft=0;
        }
        invalidate();//会导致onDraw()方法执行
    }


    private float startX;//记录按下的坐标，用于判断滑动
    private float lastX;//用于判断最终是滑动还是点击

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN://手指按下
                //1、记录按下的坐标
                startX=event.getX();
                lastX=startX;
                isEnableClick=true;
                break;
            case MotionEvent.ACTION_MOVE://手指移动
                //2、计算结束值
                float endX=event.getX();
                //3、计算偏移量
                float disX=endX-startX;
                slideLeft= (int) (slideLeft+disX);
                //4、屏蔽非法值
                if (slideLeft<0){
                    slideLeft=0;
                } else if (slideLeft>slideMax){
                    slideLeft=slideMax;
                }
                //5、刷新
                invalidate();
                //6、数据还原
                startX=event.getX();
                //如果滑动大于5个像素，点击失效
                if (Math.abs(endX-lastX)>5){
                    isEnableClick=false;
                }
                break;
            case MotionEvent.ACTION_UP://手指拿起
                if (!isEnableClick){
                    //根据移动的距离判断开关状态
                    if (slideLeft>slideMax/2){
                        isOpend=true;
                    } else{
                        isOpend=false;
                    }
                    //重新绘制
                    update();
                }
                break;
        }
        return true;
    }
}
