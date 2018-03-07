package com.liuqiang.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

/**
 * Created by liuqiang on 2018/3/6.
 */
public class PracticePieChartView extends View{
    private static final int[] colors = {Color.GRAY,Color.BLUE,Color.RED,Color.GREEN,Color.CYAN};

    private Paint mPaint = new Paint(); //初始化画笔
    private int mWidth = 0; //view宽
    private int mHeight = 0; //view高
    public PracticePieChartView(Context context) {
        super(context);
        init();
    }

    public PracticePieChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PracticePieChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public PracticePieChartView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }
    private void init(){
        mPaint.setTextSize(100);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth/2,mHeight/2);

        int radius = mWidth/2 - 10; //半径
        RectF rectF = new RectF(-radius,-radius,radius,radius);


        int total = 100;
        int[] data = {11,8,35,46};

        int startAngle = 0;

        for (int i = 0; i < data.length; i++) {
            mPaint.setColor(colors[i]);
            int sweepAngle = data[i] * 360 / total;
            if(startAngle + sweepAngle > 360){
                sweepAngle = 360 - startAngle;
            }
            canvas.drawArc(rectF,startAngle,sweepAngle,true,mPaint);

            canvas.save();
            canvas.rotate(startAngle + sweepAngle/2);
            mPaint.setColor(Color.BLACK);
//            int x = (int) (Math.cos(sweepAngle/2) * radius);
//            int y = (int) (Math.sin(sweepAngle/2) * radius);
            canvas.drawText(sweepAngle + "",radius,100,mPaint);
            canvas.restore();

            startAngle += sweepAngle;
            if(startAngle >= 360){
                break;
            }
        }
    }



    public void setData(int total,int[] data){

        invalidate();
    }
}
