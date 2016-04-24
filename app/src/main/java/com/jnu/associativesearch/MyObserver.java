package com.jnu.associativesearch;

import android.os.FileObserver;

/**
 * Created by zhuang on 2016/3/8.
 */
public class MyObserver extends FileObserver{
    @Override
    public void onEvent(int event, String path) {
        System.out.println("event:" + event + "path:" + path + "hahahahahhahahahahahah");
    }

    public MyObserver(String path) {
        super(path);
    }
}
