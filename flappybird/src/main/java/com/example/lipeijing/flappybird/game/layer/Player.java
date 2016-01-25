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

/**
 * Player
 *
 * @author: LiPeijing
 * @time: 2016/1/24 10:01
 */

public class Player extends BaseLayer{

    private float playerX;
    private float playerY;
    private float radius;

    private float speedY;
    private float acc;

    public Player(GameSurface surface) {
        super(surface);

        playerX = screenW / 2;
        playerY = screenH / 2;

        radius = 50;

        speedY = 10;
        acc = 2;
    }

    /**
     * 画图
     * @param canvas
     * @param paint
     */
    public void draw(Canvas canvas, Paint paint){
        paint.setColor(Assist.getColor(res, R.color.Red));
        switch (surface.getGameState()) {
            case Constants.GAME_START:
                canvas.drawCircle(playerX, playerY, radius, paint);
                break;
            case Constants.GAMING:
                canvas.drawCircle(playerX, playerY, radius, paint);
                break;
            case Constants.GAME_END:
                canvas.drawCircle(playerX, playerY, radius, paint);
                break;
            default:

                break;
        }

    }

    /**
     *逻辑
     */

    public void logic() {
        playerY += speedY;
        speedY += acc;

//        if(speedY >= 50){
//            speedY = 20;
//        }
//        if(speedY <= -50){
//            speedY = -20;
//        }
//        if(acc >= 10){
//            acc = 2;
//        }
//        if(acc <= -10){
//            acc = -2;
//        }

        //上边界碰撞
        if (playerY - radius <= 0){
            surface.setGameState(Constants.GAME_END);
        }

        //下边界碰撞
        if (playerY + radius >= screenH){
            surface.setGameState(Constants.GAME_END);
        }


    }

    @Override
    public void onTouchEvent(MotionEvent event) {
        speedY = -25;
    }

    /**
     * 按键点击事件
     * @param keyCode
     * @param event
     */
    public void onKeyDown(int keyCode, KeyEvent event){

    }

    public float getPlayerX() {
        return playerX;
    }

    public float getPlayerY() {
        return playerY;
    }

    public float getRadius() {
        return radius;
    }
}
