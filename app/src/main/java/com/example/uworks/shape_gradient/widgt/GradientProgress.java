package com.example.uworks.shape_gradient.widgt;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.uworks.shape_gradient.R;

/**
 * Created by UWorks on 2016/9/23.
 */
public class GradientProgress extends View {
    private int[] Colors = {Color.GREEN,Color.YELLOW,Color.RED};
    private int MaxCount;
    private int  CurrentCount;
    Shader mShader;
    private Paint mPaint;
    private int mWidth = 300,mHeight = 20;
    float progress;
    public GradientProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
        initview(context, attrs);
    }
    private void initview(Context context,AttributeSet attrs){
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.GradientProgress);
        MaxCount = array.getInt(R.styleable.GradientProgress_MaxCount,0);
        array.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        Log.d("draw","progress");
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        int round = mHeight/2;
        RectF rectbg = new RectF(0,0,mWidth,mHeight);
        canvas.drawRoundRect(rectbg,round,round,mPaint);
        mPaint.setColor(Color.WHITE);
        RectF rectroundbg = new RectF(5,5,mWidth-5,mHeight-5);
        canvas.drawRoundRect(rectroundbg,round,round,mPaint);
         if ( mShader == null){
             mShader = new LinearGradient(5,5,progress,mHeight-5,Colors,null, Shader.TileMode.MIRROR);
         }
        RectF mRectF = new RectF(5,5,progress,mHeight-5);
        mPaint.setShader(mShader);
        canvas.drawRoundRect(mRectF,round,round,mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthdMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthdMode == MeasureSpec.EXACTLY || widthdMode == MeasureSpec.AT_MOST){
            mWidth = widthSize;
        }else {
            mWidth = 0;
        }
        mHeight = heightSize;
        setMeasuredDimension(mWidth,mHeight);
    }
    public void setCurrentCount(int currentCount){
        this.CurrentCount = currentCount;
        setProgress();
    }
    private void setProgress(){
         float f = CurrentCount/MaxCount;
        progress = CurrentCount > MaxCount?MaxCount:CurrentCount*f;
        Log.d("Gradient","progress");
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                invalidate();
                break;
        }
        return true;
    }
}
