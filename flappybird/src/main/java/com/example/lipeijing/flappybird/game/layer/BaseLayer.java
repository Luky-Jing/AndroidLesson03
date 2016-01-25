package com.example.lipeijing.flappybird.game.layer;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.example.lipeijing.flappybird.game.GameSurface;

/**
 * BaseLayer
 *
 * @author: LiPeijing
 * @time: 2016/1/24 10:12
 */

public abstract class BaseLayer {

    protected GameSurface surface;
    protected Resources res;

    /**
     * 当前surface视图的宽
     */
    protected int screenW;
    /**
     * 当前surface视图的高
     */
    protected int screenH;

    public BaseLayer(GameSurface surface) {
        this.surface = surface;

        screenW = surface.getWidth();
        screenH = surface.getHeight();

        res = surface.getResources();
    }

    /**
     * 画图
     * @param canvas
     * @param paint
     */
    public abstract void draw(Canvas canvas, Paint paint);

    /**
     *逻辑
     */
    public abstract void logic();

    /**
     * 触摸事件
     * @param event
     */
    public abstract void onTouchEvent(MotionEvent event);

    /**
     * 按键点击事件
     * @param keyCode
     * @param event
     */
    public abstract void onKeyDown(int keyCode, KeyEvent event);
}
