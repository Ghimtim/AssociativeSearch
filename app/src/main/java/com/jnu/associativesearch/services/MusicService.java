package com.jnu.associativesearch.services;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.List;

public class MusicService extends Service {
    public MusicService() {

    }

    private List<ActivityManager.RunningAppProcessInfo> processList;
    private List<ActivityManager.RunningTaskInfo> taskList;
    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("bind service......");
        new Thread(new Runnable() {
            @Override
            public void run() {
                ActivityManager am = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
                while(true){
                    processList = am.getRunningAppProcesses();
                    taskList = am.getRunningTasks(10);
                    System.out.println(processList.size());
                    for(ActivityManager.RunningAppProcessInfo rapi : processList){
                        System.out.println("processName:" + rapi.processName + "importance:" + rapi.importance);
                    }

                    System.out.println("...............................");
                    int i = 0;
                    for(ActivityManager.RunningTaskInfo rti : taskList){
                        System.out.println("task:" + i + rti.topActivity.toString() + "packageName:" + rti.topActivity.getPackageName());
                        i++;
                    }

                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        return new Binder();
    }
}
