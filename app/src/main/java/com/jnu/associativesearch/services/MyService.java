package com.jnu.associativesearch.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.FileObserver;
import android.os.IBinder;
import android.util.Log;

import com.jnu.associativesearch.MyObserver;
import com.jnu.associativesearch.TestThread;

public class MyService extends Service {
    public MyService() {
        myObserver = new MyObserver("/");
    }

    private FileObserver myObserver;

    @Override
    public IBinder onBind(Intent intent) {
        Log.i("debug", "onbind ......");
        new Thread(new TestThread()).start();
        return new Binder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("service start ......");
        Log.i("debug", "aaaaaaa");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
       new Thread(new TestThread()).start();
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("service destroy ......");
    }
}
