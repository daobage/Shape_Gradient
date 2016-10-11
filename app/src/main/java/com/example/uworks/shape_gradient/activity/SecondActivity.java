package com.example.uworks.shape_gradient.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.uworks.shape_gradient.R;
import com.example.uworks.shape_gradient.widgt.SildingFinishView;

/**
 * Created by UWorks on 2016/9/29.
 */
public class SecondActivity extends AppCompatActivity implements SildingFinishView.OnsildingFinishListener {
     private SildingFinishView finishView;
    private TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_sildingfinish);
        finishView = (SildingFinishView) findViewById(R.id.silding);
        textView = (TextView) findViewById(R.id.text);
        finishView.setOnSildingFinishListener(this);
        finishView.setTouchView(textView);
    }

    @Override
    public void onSildingFinish() {
        Intent intent = new Intent();
        intent.setClass(this,ThreeActivity.class);
        finish();
        startActivity(intent);
    }
}
