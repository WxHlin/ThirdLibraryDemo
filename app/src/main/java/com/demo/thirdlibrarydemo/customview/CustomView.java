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
 * Created by Administrator on 2017/6/19 0019.
 */

public class CustomView extends View {

    private Paint paint;//画笔
    private Paint paint1;//画笔
    private Paint paint2;//画笔

    private float width = 50;
    //中心点
    private float mX = 200;
    private float mY = 200;
    private float radius = 100;//半径

    private RectF rectf;

    private int min = 0;//最小
    private int max = 100;//最大
    private int w,h;//宽高

    public CustomView(Context context) {
        super(context);
        inti();
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inti();
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inti();
    }

//    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //当前角度
        float degree = min / max * 360;

        canvas.drawCircle(mX, mY, radius, paint);//绘制圆形
        canvas.drawArc(rectf, 90, degree, false, paint1);//绘制扇形
        canvas.drawText(min + "%", mX, mY, paint2);

        if (min < max) {
            min++;
            invalidate();//强制调用onDraw()方法
        }
    }

    private void inti() {
        paint = new Paint();
        paint.setColor(Color.RED);//画笔颜色
        paint.setAntiAlias(false);//去掉锯齿
        paint.setStyle(Paint.Style.STROKE);//圆
        paint.setStrokeWidth(width);//float类型

        paint1 = new Paint();
        paint1.setColor(Color.WHITE);//画笔颜色
        paint1.setAntiAlias(false);//去掉锯齿
        paint1.setStyle(Paint.Style.STROKE);//圆
        paint1.setStrokeWidth(width);//float类型

        paint2 = new Paint();
        paint2.setColor(Color.RED);//画笔颜色
        paint2.setAntiAlias(false);//去掉锯齿
        paint.setTextAlign(Paint.Align.CENTER);//设置文字居中
        paint.setTextSize(200);//文字大小

        rectf = new RectF(100, 100, radius * 2 + 100, radius * 2 + 100);
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
            result= (int) (radius*2+width);
        } else{

        }

        return result;
    }
}
