package com.jnu.associativesearch;

/**
 * Created by zhuang on 2016/3/8.
 */
public class TestThread implements Runnable {
    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(1000);
                System.out.println("test thread ......");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
