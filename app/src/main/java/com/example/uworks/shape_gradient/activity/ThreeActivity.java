package com.example.uworks.shape_gradient.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.uworks.shape_gradient.R;
import com.example.uworks.shape_gradient.widgt.BitmapShaderView;

/**
 * Created by UWorks on 2016/9/29.
 */
public class ThreeActivity extends AppCompatActivity {
    BitmapShaderView view;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_three_activity);
        view = (BitmapShaderView) findViewById(R.id.bitmap);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThreeActivity.this,SwipActivity.class);
                startActivity(intent);
            }
        });
    }
}
