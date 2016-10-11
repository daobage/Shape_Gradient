package com.example.uworks.shape_gradient.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.uworks.shape_gradient.service.MyService;
import com.example.uworks.shape_gradient.R;

/**
 * Created by UWorks on 2016/10/10.
 */
public class ServiceActivity extends AppCompatActivity implements View.OnClickListener {
    Button bind,msg;
    MyService.MyBinder binder;
    private Intent intent;
    InnerCon con;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_service);
        initView();
        initData();
    }
private void initView(){
    bind = (Button) findViewById(R.id.bind);
    msg = (Button) findViewById(R.id.msg);
    bind.setOnClickListener(this);
    msg.setOnClickListener(this);
}
    private void initData(){
        intent = new Intent(this,MyService.class);
        con = new InnerCon();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bind:
                bindService(intent,con,BIND_AUTO_CREATE);
                break;
            case R.id.msg:
                binder.toast();
                break;
        }

    }

    class InnerCon implements ServiceConnection{
     @Override
     public void onServiceConnected(ComponentName name, IBinder service) {
        binder = (MyService.MyBinder) service;
     }

     @Override
     public void onServiceDisconnected(ComponentName name) {

     }
 }
}
