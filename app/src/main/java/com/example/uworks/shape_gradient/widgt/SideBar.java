package com.example.uworks.shape_gradient.widgt;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by UWorks on 2016/9/28.
 */
public class  SideBar extends View {
    private static String[] words = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P",
                                    "Q","R","S","T","U","V","W","X","Y","Z","#"};
    private Paint paint;
    private Rect rect;
    private int width,height;
    private int wordheight;
    private float touchy,touchx;
    private OnSelectListener listener;
    public SideBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }   
    private void init(){
        rect = new Rect();
        paint = new Paint();
        paint.setColor(Color.parseColor("#6699ff"));
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top,right, bottom);
        if (changed){
            width = getMeasuredWidth();
            Log.d("getMeasuredWidth()",width+"");
            height = getMeasuredHeight();
             wordheight = height/words.length-2;
            int textsize = (int) ((width >wordheight? wordheight : width)*3.0f/4);
           paint.setTextSize(textsize);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawWords(canvas);
    }
    private void drawWords(Canvas canvas){
        for (int i = 0;i<words.length;i++){
            String s = words[i];
            paint.getTextBounds(s,0,s.length(),rect);
            canvas.drawText(s,(width-rect.width())/2f,
                wordheight*i+(wordheight+rect.height()),paint);
        }
    }
    private String getHint(){
        int index = (int) (touchy/wordheight);
        if (index >= 0 && index<words.length){
            return words[index];
        }
        return null;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action){
                case MotionEvent.ACTION_MOVE:
                touchx = event.getX();
                touchy = event.getY();
                if(listener != null && touchx > 0){
                    listener.onSelect(getHint());
                }
                if(listener != null && touchx < 0){
                    listener.onMoveUp(getHint());
                }
                return true;
            case MotionEvent.ACTION_UP:
                touchy = event.getY();
                if(listener != null){
                    listener.onMoveUp(getHint());
                }
                return true;
        }
        return true;
    }
    //监听器，监听手指在SideBar上按下和抬起的动作
    public interface OnSelectListener{
        void onSelect(String s);
        void onMoveUp(String s);
    }

    //设置监听器
    public void setOnSelectListener(OnSelectListener listener){
        this.listener = listener;
    }
}
