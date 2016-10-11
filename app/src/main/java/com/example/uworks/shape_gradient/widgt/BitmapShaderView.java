package com.example.uworks.shape_gradient.widgt;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.view.View;

import com.example.uworks.shape_gradient.R;

/**
 * Created by UWorks on 2016/9/28.
 */
public class BitmapShaderView extends View {
    private Bitmap bitmap;
    private BitmapShader bitmapShader;
    private int bitwidth,bitheight;
    private ShapeDrawable shapeDrawable;
    public BitmapShaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    private void init(){
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.weide2);
        bitwidth = bitmap.getWidth();
        bitheight = bitmap.getHeight();
        bitmapShader = new BitmapShader(bitmap, Shader.TileMode.MIRROR, Shader.TileMode.REPEAT);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        shapeDrawable = new ShapeDrawable(new OvalShape());
        shapeDrawable.getPaint().setShader(bitmapShader);
        shapeDrawable.setBounds(20,20,bitwidth-140,bitheight);
        shapeDrawable.draw(canvas);
    }
}
