package com.example.uworks.shape_gradient.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.uworks.shape_gradient.R;
import com.example.uworks.shape_gradient.widgt.SideBar;

public class MainActivity extends AppCompatActivity implements SideBar.OnSelectListener {

    private SideBar sideBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sideBar = (SideBar) findViewById(R.id.sidebar);
        sideBar.setOnSelectListener(this);

    }

    @Override
    public void onSelect(String s) {
        Log.d("MainActivity ","-----"+s);
    }

    @Override
    public void onMoveUp(String s) {
        Intent intent = new Intent(this,SecondActivity.class);
        startActivity(intent);
    }

}
