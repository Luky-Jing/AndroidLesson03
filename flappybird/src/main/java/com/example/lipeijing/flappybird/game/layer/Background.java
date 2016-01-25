package com.example.lipeijing.flappybird.game.layer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.example.lipeijing.flappybird.R;
import com.example.lipeijing.flappybird.game.GameSurface;
import com.example.lipeijing.flappybird.utils.Assist;

/**
 * Background
 *
 * @author: LiPeijing
 * @time: 2016/1/24 10:02
 */

public class Background extends BaseLayer{

    private int color;

    public Background(GameSurface surface) {
        super(surface);
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {

        paint.setColor(Color.CYAN);

        canvas.drawRect(0, 0, screenW, screenH, paint);
    }

    @Override
    public void logic() {

    }

    @Override
    public void onTouchEvent(MotionEvent event) {

    }

    @Override
    public void onKeyDown(int keyCode, KeyEvent event) {

    }
}
