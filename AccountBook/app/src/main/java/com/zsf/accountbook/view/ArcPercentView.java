package com.zsf.accountbook.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.zsf.accountbook.R;

/**
 * Created by zsf on 2017/3/17.
 */

public class ArcPercentView extends View {

    private int mHeight;
    private int mWidth;

    private Paint mCircle;
    private float mCircleCenter;
    private float mCircleRadius;

    private Paint mArc;
    private RectF mArcRectF;
    private float mSweepAngle;
    private float mSweepValue = 66;

    private Paint mText;
    private String mShowTxt;
    private float mTextSize;

    public ArcPercentView(Context context) {
        super(context);
    }

    public ArcPercentView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ArcPercentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(mWidth, mHeight);
        initView();
    }

    private void initView() {
        float length = 0;
        if (mHeight >= mWidth) {
            length = mWidth;
        } else {
            length = mHeight;
        }

        mCircleCenter = length / 2;//圆心
        mCircleRadius = (float) (length * 0.65 / 2);//半径
        mCircle = new Paint();
        mCircle.setAntiAlias(true);//
        mCircle.setColor(getResources().getColor(R.color.blue));

        mArcRectF = new RectF(
                (float) (length * 0.1),
                (float) (length * 0.1),
                (float) (length * 0.9),
                (float) (length * 0.9)
        );
        mSweepAngle = (mSweepValue ) * 360f;
        mArc = new Paint();
        mArc.setAntiAlias(true);
        mArc.setColor(getResources().getColor(R.color.green));
        mArc.setStrokeWidth((float) (length * 0.1));
        mArc.setStyle(Paint.Style.STROKE);

        mShowTxt = setShowTxt(mShowTxt);
        mTextSize = setTextSize();
        mText = new Paint();
        mText.setTextSize(mTextSize);
        mText.setTextAlign(Paint.Align.CENTER);
    }

    private float setTextSize() {
        this.invalidate();
        return 20;

    }

    public String setShowTxt(String text) {
        mShowTxt = text;
        this.invalidate();
        return mShowTxt;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 绘制圆
        canvas.drawCircle(mCircleCenter,mCircleCenter,mCircleRadius,mCircle);
        // 绘制弧线
        canvas.drawArc(mArcRectF, 270, mSweepAngle, false, mArc);
//        // 绘制文字
//        canvas.drawText(mShowTxt, 0, mShowTxt.length(),
//                mCircleCenter, mCircleCenter + (mTextSize / 4), mText);
    }

    /**
     * 强制刷新
     */
    public void forceInvalidate() {
        this.invalidate();
    }

    /**
     * 设置进度
     * @param sweepValue
     */
    public void setSweepValue(float sweepValue) {
        if (sweepValue != 0) {
            mSweepValue = sweepValue;
        } else {
            mSweepValue = 25;
        }
        this.invalidate();
    }
}
