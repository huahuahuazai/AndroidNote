package cf.android666.applibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ToggleButton;

/**
 * Created by jixiaoyong on 2018/2/18.
 */

public class ToggleButtonPlus extends ToggleButton {

    private boolean isChecked = false;

    private int mBackgroundColor;
    private int mForwardColor;
    private int mStrokeColor;

    private int mHeight;
    private int mWidth;

    public ToggleButtonPlus(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        TypedArray array = context.obtainStyledAttributes(attrs,
                R.styleable.ToggleButtonPlus, defStyleAttr, defStyleRes);

        mBackgroundColor = array.getColor(R.styleable.ToggleButtonPlus_background_color, 0xff09bb07);
        mForwardColor = array.getColor(R.styleable.ToggleButtonPlus_forward_color, 0xfff5f5f5);
        mStrokeColor = array.getColor(R.styleable.ToggleButtonPlus_stroke_color, 0xddaaaaab);

    }

    public ToggleButtonPlus(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    public ToggleButtonPlus(Context context, AttributeSet attrs) {
        this(context, attrs,0,0);
    }

    public ToggleButtonPlus(Context context) {
        this(context,null,0,0);
    }


    @Override
    protected void onDraw(Canvas canvas) {

        Log.d("TAG", "onDraw: " + isChecked);

        int circleR = mHeight / 2;

        Paint backgroundPaint = new Paint();
        RectF rectF = new RectF(0, 0, mWidth, mHeight);

        Paint forwardPaint = new Paint();
        forwardPaint.setColor(mForwardColor);
        int cy = mHeight / 2;
        int cx = 0;

        if (isChecked) {
            backgroundPaint.setColor(mBackgroundColor);
            cx = mWidth - cy;
        } else {
            backgroundPaint.setColor(mForwardColor);
            cx = cy;
        }

        canvas.drawRoundRect(rectF,circleR,circleR, backgroundPaint);
        canvas.drawCircle(cx,cy,circleR,forwardPaint);

        forwardPaint.setStyle(Paint.Style.STROKE);
        forwardPaint.setColor(mStrokeColor);
        forwardPaint.setStrokeWidth(3);
        canvas.drawCircle(cx,cy,circleR,forwardPaint);

        backgroundPaint.setStyle(Paint.Style.STROKE);
        backgroundPaint.setColor(mStrokeColor);
        backgroundPaint.setStrokeWidth(3);
        canvas.drawRoundRect(rectF,circleR,circleR, backgroundPaint);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mHeight = getMeasuredHeight();
        mWidth = getMeasuredWidth();

    }


    @Override
    public void setChecked(boolean checked) {
        super.setChecked(checked);
    }

    private void refresh() {
        isChecked = !isChecked;

        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                refresh();
                break;
        }

        return super.onTouchEvent(event);
    }
}
