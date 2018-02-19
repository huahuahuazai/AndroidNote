package cf.android666.applibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.util.AttributeSet;


/**
 * Created by jixiaoyong on 2018/2/19.
 */

public class ProgressButton extends android.support.v7.widget.AppCompatButton {

    private int mHeight;
    private int mWidth;

    private float mProgress;

    private int mMaxProgress;
    private int mRadius;

    private int mBackgroundColor;
    private int mForwardColor;
    private int mStorkeColor;
    private float mTextSize;
    public ProgressButton(Context context) {
        this(context, null, 0);
    }


    public ProgressButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public ProgressButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ProgressButton);
        mBackgroundColor = array.getColor(R.styleable.ProgressButton_background_color, 0xfff5f5f5);
        mForwardColor = array.getColor(R.styleable.ProgressButton_forward_color, 0xff09bb07);
        mStorkeColor = array.getColor(R.styleable.ProgressButton_stroke_color, 0xddaaaaab);
        mMaxProgress = array.getInt(R.styleable.ProgressButton_max_progress, 100);
        mRadius = array.getDimensionPixelSize(R.styleable.ProgressButton_radius, 50);
        mTextSize = array.getFloat(R.styleable.ProgressButton_textSize, 35);
    }

    @Override
    protected void onDraw(Canvas canvas) {

//        mProgress = 0.2f;

        Paint backgroundPaint = new Paint();
        backgroundPaint.setColor(mBackgroundColor);
        RectF backgroundRectF = new RectF(0, 0, mWidth, mHeight);
        canvas.drawRoundRect(backgroundRectF, mRadius, mRadius, backgroundPaint);

        Paint progressPaint = new Paint();
        progressPaint.setColor(mForwardColor);
        progressPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        RectF progressRectF = new RectF(0, 0, mWidth * mProgress, mHeight);
        canvas.drawRoundRect(progressRectF, mRadius, mRadius, progressPaint);

        String text = "进度 " + mProgress * mMaxProgress + "%";
        Paint textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        Rect bounds = new Rect();
        textPaint.setTextSize(mTextSize);
        textPaint.getTextBounds(text,0,text.length(),bounds);
        int startX = (mWidth - bounds.width()) / 2;
        int startY = (mHeight + bounds.height()) / 2;
        canvas.drawText(text, startX, startY, textPaint);

        backgroundPaint.setColor(mStorkeColor);
        backgroundPaint.setStyle(Paint.Style.STROKE);
        backgroundPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        backgroundPaint.setStrokeWidth(3);
        canvas.drawRoundRect(backgroundRectF, mRadius, mRadius, backgroundPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize;
        } else {
            mWidth = 100;
            if (widthMode == MeasureSpec.AT_MOST) {
                mWidth = Math.min(mWidth, widthSize);
            }
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
        } else {
            mHeight = 50;
            if (heightMode == MeasureSpec.AT_MOST) {
                mHeight = Math.min(mHeight, heightSize);
            }
        }

        int widthSpec = MeasureSpec.makeMeasureSpec(mWidth, MeasureSpec.EXACTLY);
        int heightSpec = MeasureSpec.makeMeasureSpec(mHeight, MeasureSpec.EXACTLY);
        //使父布局为button分配对应大小控件
        super.onMeasure(widthSpec, heightSpec);

    }

    public float getProgress() {
        return mProgress;
    }

    public void setProgress(float mProgress) {
        this.mProgress = mProgress;
        invalidate();
    }


}
