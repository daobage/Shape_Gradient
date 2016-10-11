package com.example.uworks.shape_gradient.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by UWorks on 2016/10/10.
 */
public class MyService extends Service {
      MyBinder binder;
    @Override
    public void onCreate() {
        super.onCreate();
        binder = new MyBinder();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(getApplicationContext(),"绑定服务",Toast.LENGTH_SHORT).show();
        return binder;
    }
    public class MyBinder extends Binder{
        public void toast(){
            Toast.makeText(getApplicationContext(),"获取服务数据",Toast.LENGTH_SHORT).show();
        }
    }

}
