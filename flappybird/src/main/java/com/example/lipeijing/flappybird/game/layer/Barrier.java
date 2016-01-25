package com.example.lipeijing.flappybird.game.layer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.example.lipeijing.flappybird.R;
import com.example.lipeijing.flappybird.game.GameSurface;
import com.example.lipeijing.flappybird.utils.Assist;
import com.example.lipeijing.flappybird.utils.Constants;

import java.util.Random;

/**
 * Barrier
 *
 * @author: LiPeijing
 * @time: 2016/1/24 10:03
 */

public class Barrier extends BaseLayer{

    private float spaceH;   //障碍的间隙

    private float barrier1X;
    private float barrier1H;

    private float barrier2X;
    private float barrier2H;

    private float distance;     //障碍的间隔
    private float barrierW;     //障碍的宽
    private float barrierY;     //障碍的高

    private float speed;    //障碍的速度

    private float playerX, playerY;
    private float radius;

    public Barrier(GameSurface surface) {

        super(surface);

        spaceH = screenH / 4;
        barrierW = 120;
        distance = screenW / 2 - barrierW / 2;
        barrierY = 0;
        speed = 10;

        barrier1X = screenW;
        barrier1H = new Random().nextInt((int) (screenH - spaceH));

        barrier2X = barrier1X + barrierW + distance;
        barrier2H = new Random().nextInt((int) (screenH - spaceH));
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {

        paint.setColor(Color.GREEN);

        //第一个上
        canvas.drawRect(barrier1X, barrierY, barrier1X + barrierW, barrierY + barrier1H, paint);
        //第一个下
        canvas.drawRect(barrier1X, barrier1H + spaceH, barrier1X + barrierW, screenH, paint);

        //第二个上
        canvas.drawRect(barrier2X, barrierY, barrier2X + barrierW, barrierY + barrier2H, paint);
        //第二个下
        canvas.drawRect(barrier2X, barrier2H + spaceH, barrier2X + barrierW, screenH, paint);
    }

    @Override
    public void logic() {
        barrier1X -= speed;
        barrier2X -= speed;
//        barrier2X = barrier1X + barrierW + distance;

        if(barrier1X + barrierW <= 0){
            barrier1X = screenW;
            barrier1H = getBarrierRanH();
        }
        if(barrier2X + barrierW <= 0){
            barrier2X = barrier1X + barrierW + distance;
            barrier2H = getBarrierRanH();
        }

        //主角 与 障碍的碰撞测试
        boolean isColl1 = circleAndRect(playerX, playerY, radius, barrier1X, barrierY, barrierW,
                barrier1H);
        boolean isColl2 = circleAndRect(playerX, playerY, radius, barrier1X, barrier1H + spaceH,
                barrierW, screenH - barrier1H - spaceH);
        boolean isColl3 = circleAndRect(playerX, playerY, radius, barrier2X, barrierY, barrierW,
                barrier2H);
        boolean isColl4 = circleAndRect(playerX, playerY, radius, barrier2X, barrier2H + spaceH,
                barrierW, screenH - barrier2H - spaceH);

        if (isColl1 || isColl2 || isColl3 || isColl4){
            surface.setGameState(Constants.GAME_END);
        }
    }

    @Override
    public void onTouchEvent(MotionEvent event) {

    }

    @Override
    public void onKeyDown(int keyCode, KeyEvent event) {

    }

    public float getBarrierRanH(){
        return new Random().nextInt((int) (screenH - spaceH));
    }


    private boolean circleAndRect(float circleX, float circleY, float circleR, float rectX, float
            rectY, float rectW, float rectH) {
        if (circleX + circleR < rectX) {
            return false;
        } else if (circleX - circleR > rectX + rectW) {
            return false;
        } else if (circleY + circleR < rectY) {
            return false;
        } else if (circleY - circleR > rectY + rectH) {
            return false;
        } else if (Math.pow(rectX - circleX, 2) + Math.pow(rectY - circleY, 2) > circleR *
                circleR && circleX < rectX && circleY < rectX) {
            return false;
        } else if (Math.pow(rectX + rectW - circleX, 2) + Math.pow(rectY - circleY, 2) > circleR *
                circleR && circleX > rectX + rectW && circleY < rectY) {
            return false;
        } else if (Math.pow(rectX - circleX, 2) + Math.pow(rectY + rectH - circleY, 2) > circleR *
                circleR && circleX < rectX && circleY > rectY + rectH) {
            return false;
        } else if (Math.pow(rectX + rectW - circleX, 2) + Math.pow(rectY + rectH - circleY, 2) >
                circleR * circleR && circleX > rectX + rectW && circleY > rectY + rectH) {
            return false;
        }

        return true;
    }

    public void setPlayerX(float playerX) {
        this.playerX = playerX;
    }

    public void setPlayerY(float playerY) {
        this.playerY = playerY;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }
}
