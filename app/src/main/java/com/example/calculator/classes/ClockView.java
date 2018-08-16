package com.example.calculator.classes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.example.calculator.R;

import java.util.Calendar;

public class ClockView extends View {

    private static final int PADDING_SPACE = 50;
    private static final int ZERO = 0;
    private static final int HAND_TRUNCATION = 20;
    private static final int HOUR_HAND_TRUNCATION = 7;
    private static final long DELAY = 500;
    private static final float FONT_SIZE_VALUE = 12;
    private static final int TWO = 2;
    private static final double LOCAL_ANGEL = 30;
    private static final int HOUR = 12;
    private static final int MINUTES = 60;
    private static final float DRAW_HAND_POWER = 5;
    private static final double ANGEL_DIVIDE = 6;
    private static final int ANGEL_SUBTRACT = 3;
    private static final int PADDING_DRAW_CIRCLE = 10;
    private static final double Y_POSITION = 35;

    private int width;
    private int padding;
    private int height;
    private int fontSize;
    private int handTruncation;
    private int hourHandTruncation;
    private int radius;
    private Paint paint;
    private boolean isInit;
    private int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
    private Rect rect;

    public ClockView(Context context) {
        super(context);
    }

    public ClockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initClock(){
        width = getWidth();
        height = getHeight();
        padding = PADDING_SPACE;
        fontSize = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, FONT_SIZE_VALUE,
                getResources().getDisplayMetrics());
        int min = Math.min(height, width);
        radius = min / TWO - padding;
        handTruncation = min / HAND_TRUNCATION;
        hourHandTruncation = min / HOUR_HAND_TRUNCATION;
        paint = new Paint();
        isInit = true;
        rect = new Rect();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (!isInit){
            initClock();
        }

        drawCircle(canvas);
        drawCenter(canvas);
        drawNumeral(canvas);
        drawHands(canvas);

        postInvalidateDelayed(DELAY);
        invalidate();
    }

    private void drawHand(Canvas canvas, double loc, boolean isHour){
        double angel = Math.PI * loc / LOCAL_ANGEL - Math.PI / TWO;
        int handRadius = isHour ? radius - handTruncation - hourHandTruncation : radius - handTruncation;
        canvas.drawLine(width / TWO, height / TWO,
                (float)(width / TWO + Math.cos(angel) * handRadius),
                (float)(height / TWO + Math.sin(angel) * handRadius),
                paint);
    }

    private void drawHands(Canvas canvas) {
        Calendar calendar = Calendar.getInstance();
        float hour = calendar.get(Calendar.HOUR_OF_DAY);
        hour = hour > HOUR ? hour - HOUR : hour;
        drawHand(canvas, (hour + calendar.get(Calendar.MINUTE) / MINUTES) * DRAW_HAND_POWER, true);
        drawHand(canvas, calendar.get(Calendar.MINUTE), false);
        drawHand(canvas, calendar.get(Calendar.SECOND), false);
    }

    private void drawNumeral(Canvas canvas) {
        paint.setTextSize(fontSize);

        for (int number : numbers){
            String tmp = String.valueOf(number);
            paint.getTextBounds(tmp, ZERO, tmp.length(), rect);
            double angel = Math.PI / ANGEL_DIVIDE * (number - ANGEL_SUBTRACT);
            int x = (int)(width / TWO + Math.cos(angel) * radius - rect.width() / TWO);
            int y = (int)(width / TWO + Math.sin(angel) * radius - rect.width() / TWO + Y_POSITION);
            canvas.drawText(tmp, x, y, paint);
        }


    }

    private void drawCenter(Canvas canvas) {
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(width / TWO, height / TWO, HOUR, paint);
    }

    private void drawCircle(Canvas canvas) {
        paint.reset();
        paint.setColor(getResources().getColor(R.color.md_white_1000));
        paint.setStrokeWidth(DRAW_HAND_POWER);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        canvas.drawCircle(width / TWO, height / TWO, radius + padding - PADDING_DRAW_CIRCLE, paint);
    }
}
