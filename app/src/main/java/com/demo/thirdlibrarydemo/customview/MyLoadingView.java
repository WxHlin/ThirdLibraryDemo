package com.demo.thirdlibrarydemo.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/6/23 0023.
 */

public class MyLoadingView extends View{

    private Paint paint;//画圆
    private float lineWidth=30;//圆的线宽
    private float mX=150;//圆中心点x轴的坐标
    private float mY=150;//圆中心点y轴的坐标
    private float radius=100;//圆的半径

    private Paint paint1;
    private RectF rectF;//绘制扇形所需要的矩形
    private int min = 0;//最小
    private int max = 100;//最大

    private int w,h;//自定义view的宽高

    private Paint paint2;

    public MyLoadingView(Context context) {
        super(context);
        init();
    }


    public MyLoadingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyLoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        w=getSize(widthMeasureSpec);
        h=getSize(heightMeasureSpec);
        setMeasuredDimension(w,h);
    }

    private int getSize(int spec) {
        int result=-1;
        int mode=MeasureSpec.getMode(spec);
        int size=MeasureSpec.getMode(spec);
        if (mode==MeasureSpec.AT_MOST||mode==MeasureSpec.UNSPECIFIED){
            result= (int) (radius*2+lineWidth*2)+20;
        } else{

        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float degree=min/max*360;

        canvas.drawCircle(mX,mY,radius,paint);//画圆
        canvas.drawArc(rectF,90,degree,false,paint1);
        canvas.drawText(min + "%", mX, mY, paint2);

        if (min<max){
            min++;
            invalidate();//强制调用onDraw()方法
        }

    }

    private void init() {
        paint=new Paint();
        paint.setAntiAlias(true);//去锯齿
        paint.setColor(Color.GREEN);//画笔颜色
        paint.setStyle(Paint.Style.STROKE);//圆
        paint.setStrokeWidth(lineWidth);//圆的线宽

        paint1=new Paint();
        paint1.setAntiAlias(true);
        paint1.setColor(Color.BLACK);
        paint1.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(lineWidth);//圆的线宽
        rectF=new RectF(mX-lineWidth/2-radius,mY-radius-lineWidth/2,mX+lineWidth/2+radius,mY+radius+lineWidth/2);

        paint2 = new Paint();
        paint2.setColor(Color.RED);//画笔颜色
        paint2.setAntiAlias(false);//去掉锯齿
        paint2.setTextAlign(Paint.Align.CENTER);//设置文字居中
        paint2.setTextSize(50);//文字大小
    }
}
