package com.example.uworks.shape_gradient.widgt;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.Scroller;

/**
 * Created by UWorks on 2016/9/29.
 */
public class SildingFinishView extends FrameLayout{
    /* 父布局*/
    private ViewGroup mParentView;
    /* 处理滑动逻辑的view*/
    private View touchView;
    /*滑动的最小距离*/
    private int mTouchSlop;
    /*按下的x坐标*/
    private int downX;
    private int downY;
    private int tempX;
    /*滑动类*/
    private Scroller mScroller;
    /**view的宽度*/
    private int viewWidth;
    private boolean isSilding;
    private boolean isFinish;
    OnsildingFinishListener listener;
    public SildingFinishView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mScroller = new Scroller(context);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed){
            mParentView = (ViewGroup) this.getParent();
            viewWidth = this.getWidth();
        }
    }
    public void setTouchView(View touchView){
        this.touchView = touchView;
    }
    public void setOnSildingFinishListener(OnsildingFinishListener listener){
        this.listener = listener;
    }
    /*滚动出界面*/
    private void scollRight(){
        final int delta = (viewWidth+mParentView.getScrollX());
        mScroller.startScroll(mParentView.getScrollX(),0,-delta+1,0,Math.abs(delta));
        postInvalidate();
    }
    /*滚动到其实位置*/
    private void scrollOrigin(){
        int dalta = mParentView.getScrollX();
        mScroller.startScroll(mParentView.getScrollX(),0,-dalta,0,Math.abs(dalta));
        postInvalidate();
    }
    /*touchview 是Abslistview */
    private boolean isTouchonAbslistView(){
        return touchView instanceof AbsListView? true:false;
    }
    /*touchview 是ScrollView*/
    private boolean isTouchonScrollView(){
        return touchView instanceof ScrollView ? true:false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                downX = tempX = (int) event.getRawX();
                downY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveX = (int) event.getRawX();
                int deltaX = tempX - moveX;
                tempX = moveX;
                if (Math.abs(moveX-downX) > mTouchSlop
                        && Math.abs((int)event.getRawY()-downY) < mTouchSlop){
                    isSilding = true;
                    if (isTouchonAbslistView()){
                        MotionEvent cancelEvent = MotionEvent.obtain(event);
                        cancelEvent.setAction(MotionEvent.ACTION_CANCEL|
                                event.getActionIndex() << MotionEvent.ACTION_POINTER_INDEX_SHIFT);
                        touchView.onTouchEvent(cancelEvent);
                    }
                }
                if (moveX - downX >= 0 && isSilding)
                {
                    mParentView.scrollBy(deltaX,0);
                    if (isTouchonScrollView()||isTouchonAbslistView())
                        return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                isSilding = false;
                if (mParentView.getScaleX() <= -viewWidth/2)
                {
                    isFinish = true;
                    scollRight();
                }else {
                    scrollOrigin();
                    isFinish = false;
                }
                break;
        }
        return true;
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()){
            mParentView.scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            postInvalidate();
            if (mScroller.isFinished()){
                  if (listener != null){
                      listener.onSildingFinish();
                  }
            }
        }
    }
    public interface OnsildingFinishListener{
        void onSildingFinish();
    }
}
