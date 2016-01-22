package com.example.lipeijing.androidlesson03;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * MyView
 *
 * @author: LiPeijing
 * @time: 2016/1/22 8:21
 */

public class MyView extends View {

    private Paint paint;
    private int x;
    private int y;

    public MyView(Context context) {
        super(context);
        init();
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * 初始化
     */
    private void init(){
        paint = new Paint();

        paint.setColor(Color.RED);
        paint.setAntiAlias(true);

        x = 0;
        y = 0;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        paint.setColor(Color.RED);  //设置画笔颜色
        paint.setStrokeWidth(15);   //设置画笔宽度
        //画线
        canvas.drawLine(getWidth(), getHeight(), x, y, paint);
//        drawTest(canvas);
//        drawPaith(canvas);
//        drawBitmap(canvas);
    }

    private void drawTest(Canvas canvas){
        paint.setColor(Color.RED);  //设置画笔颜色
        paint.setStyle(Paint.Style.STROKE); //设置画笔Style     只画不填充
        paint.setStrokeWidth(15);   //设置画笔宽度
        //画线
        canvas.drawLine(x, y, getWidth(), getHeight(), paint);

        //画圆
        paint.setStyle(Paint.Style.FILL);   //填充
        canvas.drawCircle(200, 100, 100, paint);

        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);   //填充
        canvas.drawCircle(200, 100, 100, paint);

        //画矩形
        paint.setColor(Color.BLUE);
        canvas.drawRect(100, 500, 200, 600, paint);

        //画三角形
//        canvas.drawRoundRect(100, 700, 200, 800, 10, 10, paint);
    }

    /**
     * 路径绘制
     * @param canvas
     */
    private void drawPaith(Canvas canvas){
        Path path = new Path();
        path.moveTo(100, 100);
        path.lineTo(100, 300);
        path.lineTo(200, 250);

        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);

        canvas.drawPath(path, paint);

        Path path1 = new Path();
        path1.addCircle(500, 500, 200, Path.Direction.CW);
        path1.addCircle(500, 500, 100, Path.Direction.CCW);
        path1.moveTo(500, 300);
        path1.lineTo(500, 700);
        path1.moveTo(300, 500);
        path1.lineTo(700, 500);

        paint.setStrokeWidth(15);
        paint.setColor(Color.BLUE);
//        paint.setStyle(Paint.Style.STROKE);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawPath(path1, paint);
    }

    private void drawBitmap(Canvas canvas){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

//        canvas.drawBitmap(bitmap, 500, 500, paint);

        //只在当前画布有效
//        canvas.save();  //保存当前
//        canvas.rotate(90);
//        canvas.translate(getWidth() / 2, getHeight() / 2);
        canvas.drawBitmap(bitmap, 100, 100, paint);
//        canvas.restore();   //释放当前
    }

    public boolean onTouchEvent(MotionEvent event){
        x = (int)event.getX();
        y = (int)event.getY();

        invalidate();

        return super.onTouchEvent(event);
    }
}
