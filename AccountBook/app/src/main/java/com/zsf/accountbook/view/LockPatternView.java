package com.zsf.accountbook.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.zsf.accountbook.R;

/**
 * Created by zsf on 2017/7/11.
 */

public class LockPatternView extends View {

    private Point[][] points = new Point[3][3];
    private boolean isInit;

    private float width;
    private float height;
    float offsetX,offsetY;

    private Bitmap mPointNormal;
    private Bitmap mPointPressed;
    private Bitmap mPointError;
    private Bitmap mLinePressed;
    private Bitmap mLineError;
    private float mBitmapR;

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public LockPatternView(Context context) {
        super(context);
    }

    public LockPatternView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LockPatternView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (!isInit){
            initPoints();
        }
        point2Canvas(canvas);
    }

    private void point2Canvas(Canvas canvas) {
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points[i].length; j++) {
                Point point = points[i][j];
                if (point.state == Point.STATE_PRESSED){
                    canvas.drawBitmap(mPointPressed,point.x - mBitmapR,point.y,mPaint);
                } else if (point.state == Point.STATE_ERROR){
                    canvas.drawBitmap(mPointError,point.x - mBitmapR,point.y,mPaint);
                } else {
                    canvas.drawBitmap(mPointNormal,point.x - mBitmapR,point.y,mPaint);
                }
            }
        }
    }

    private void initPoints() {
        width = getWidth();
        height = getHeight();

        if (width > height){
            offsetX = (width - height) / 2;
            width = height;
        } else {
            offsetY = (height - width) / 2;
            height = width;
        }

        mPointNormal = BitmapFactory.decodeResource(getResources(), R.drawable.oval_normal);
        mPointError = BitmapFactory.decodeResource(getResources(), R.drawable.oval_error);
        mPointPressed = BitmapFactory.decodeResource(getResources(), R.drawable.oval_presse);

        mLineError = BitmapFactory.decodeResource(getResources(),R.drawable.liner_error);
        mLinePressed = BitmapFactory.decodeResource(getResources(),R.drawable.liner_pressed);

        points[0][0] = new Point(offsetX + width / 4,offsetY + width / 4);
        points[0][1] = new Point(offsetX + width / 2,offsetY + width / 4);
        points[0][2] = new Point(offsetX + width - width / 4,offsetY + width / 4);

        points[1][0] = new Point(offsetX + width / 4,offsetY + width / 2);
        points[1][1] = new Point(offsetX + width / 2,offsetY + width / 2);
        points[1][2] = new Point(offsetX + width - width / 4,offsetY + width / 2);

        points[2][0] = new Point(offsetX + width / 4,offsetY + width - width / 4);
        points[2][1] = new Point(offsetX + width / 2,offsetY + width - width / 4);
        points[2][2] = new Point(offsetX + width - width / 4,offsetY + width - width / 4);

        mBitmapR = mPointNormal.getHeight() / 2;

    }

    public static class Point{
        //正常
        public static int STATE_NORMAL = 0;
        //选中
        public static int STATE_PRESSED = 1;
        //错误
        public static int STATE_ERROR = 2;
        public float x,y;
        public int index = 0,state = 0;
        public Point(){}
        public Point(float x,float y){
            this.x = x;
            this.y = y;
        }

    }
}
