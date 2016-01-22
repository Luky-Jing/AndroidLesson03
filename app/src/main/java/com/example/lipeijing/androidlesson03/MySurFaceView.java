package com.example.lipeijing.androidlesson03;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

import java.util.Random;

/**
 * MySurFaceView
 *
 * @author: LiPeijing
 * @time: 2016/1/22 10:18
 */

public class MySurFaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable{

    public final static String TAG = "MySurFaceView";

    private SurfaceHolder holder;

    private Canvas canvas;
    private Paint paint;

    private Thread thread;
    private Boolean flag;

    private float x, y;
    private float speedX, speedY;
    private float radius;

    private int color;

    public MySurFaceView(Context context) {
        super(context);
        init();
    }

    public MySurFaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * 初始化
     */
    private void init(){
        holder = getHolder();
        holder.addCallback(this);

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);

    }

    private void initGame(){
        x = 0;
        y = 0;
        radius = 50;
        speedX = 10;
        speedY = 20;
        color = Color.BLUE;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.i(TAG, "surfaceCreated");

        initGame();

        flag = true;

        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.i(TAG, "surfaceChanged");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.i(TAG, "surfaceDestroyed");
        flag = false;
    }

    private void myDraw(Canvas canvas){
        paint.setColor(Color.WHITE);
        canvas.drawRect(0, 0, getWidth(), getHeight(), paint);

        paint.setColor(color);
        canvas.drawCircle(x, y, radius, paint);

    }

    /**
     * 逻辑
     */
    private void logic(){
        x += speedX;
        y += speedY;

        if(x >= getWidth() || x < 0){
            speedX = -speedX;
        }
        if(y >= getHeight() || y < 0){
            speedY = -speedY;
        }
    }

    public boolean onTouchEvent(MotionEvent event){
        int x = (int)getX();
        int y = (int)getY();
        

        radius = new Random().nextInt(10) + 50;

        return true;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event){

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void run() {
        while(flag){
            long start = System.currentTimeMillis();

            canvas = holder.lockCanvas();   //上锁
            if(null != canvas){
                myDraw(canvas);
                holder.unlockCanvasAndPost(canvas); //解锁
            }
            logic();

            long end = System.currentTimeMillis();

            if(end - start < 50){
                try {
                    thread.sleep(50 - (end - start));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
