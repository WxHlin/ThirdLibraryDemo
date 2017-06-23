package com.demo.thirdlibrarydemo.customviewpager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by Administrator on 2017/6/23 0023.
 */

public class MyCustomViewPager extends ViewGroup{

    /**
     * 手势识别器
     * 1、定义出来
     * 2、实例化--吧想要的方法重写
     * 3、在onTouchEvent()方法中传递给收拾识别器
     */
    private GestureDetector gestureDetector;//定义手势识别器
    private int currentIndex;//当前页面下标位置

    private Scroller scroller;//用于解决回弹生硬的系统提供的类


    public MyCustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);

        initView(context);
    }


    private void initView(Context context){
        scroller=new Scroller(context);
        //实例化手势识别器
        gestureDetector=new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){

            //长按
            @Override
            public void onLongPress(MotionEvent e) {
                super.onLongPress(e);
            }
            /**
             * 滑动
             * @param e1
             * @param e2
             * @param distanceX  x轴滑动了的距离
             * @param distanceY  y轴滑动了的距离
             * @return
             */
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

                /**
                 * 第一个参数：要在x轴移动的距离
                 * 第二个参数：要在y轴移动的距离
                 */
                scrollBy((int)distanceX,getScrollY());//
                return true;
            }
            //双击
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                return super.onDoubleTap(e);
            }
        });
    }


    /**
     * 抽象方法   继承ViewGroup必需写
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //遍历每个孩子在屏幕的坐标
        for (int i = 0; i <getChildCount() ; i++) {
            View childView=getChildAt(i);
            childView.layout(i*getWidth(),0,(i+1)*getWidth(),getHeight());
        }
    }

    private float startX;//记录初始的x轴的坐标

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);

        gestureDetector.onTouchEvent(event);//把事件传递给手势识别器

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                startX=event.getX();//记录坐标
                break;

            case MotionEvent.ACTION_MOVE:

                break;

            case MotionEvent.ACTION_UP:
                float endX=event.getX();//新的坐标
                int tempIndex=currentIndex;
                if (startX-endX>getWidth()/2){
                    //显示下一个页面
                    tempIndex++;
                } else if(endX-startX>getWidth()/2){
                    //显示上一个页面
                    tempIndex--;
                }
                scrollToViewPager(tempIndex);
                break;
        }

        return true;//重写后要返回true
    }

    /**
     * 屏蔽非法值，根据位置移动到指定位置
     * @param tempIndex
     */
    public void scrollToViewPager(int tempIndex) {
        if (tempIndex<0){
            tempIndex=0;
        }
        if (tempIndex>getChildCount()-1){
            tempIndex=getChildCount()-1;
        }
        //赋值给当前位置
        currentIndex=tempIndex;

        if (mOnPageChangeListener!=null){
            mOnPageChangeListener.onScrollToPage(currentIndex);
        }

        int distanceX=currentIndex*getWidth()-getScrollX();

        //移动到指定位置
//        scrollTo(currentIndex*getWidth(),getScrollY());//回弹生硬
//        scroller.startScroll(getScrollX(),getScrollY(),distanceX,0);//系统方法解决回弹生硬
        scroller.startScroll(getScrollX(),getScrollY(),distanceX,0,Math.abs(distanceX));//系统方法解决回弹生硬
        invalidate();
    }

    @Override
    public void computeScroll() {
//        super.computeScroll();
        if (scroller.computeScrollOffset()){
            //得到移动这一小段对应的坐标
            float currX=scroller.getCurrX();
            scrollTo((int) currX,0);
            invalidate();
        }
    }


    /**
     * 监听页面的改变
     */
    public interface onPageChangeListener{
        /**
         * 当页面改变的时候回调这个方法
         * @param position    当前页面的下标
         */
        void onScrollToPage(int position);
    }

    private onPageChangeListener mOnPageChangeListener;

    public void setOnPageChangeListener(onPageChangeListener onPageChangeListener){
        mOnPageChangeListener=onPageChangeListener;
    }

}
