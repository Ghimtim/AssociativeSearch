package com.jnu.associativesearch.services;

import android.app.Notification;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;


public class Music2Service extends NotificationListenerService{

    public static final String ACTION = "android.service.notification.NotificationListenerService";
    public Music2Service() {
    }



    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        super.onNotificationPosted(sbn);

        if(sbn == null){
            Log.e("nolistener", "onNotificationPosted: " );
        }else {
            if (sbn.getNotification() != null) {
                Bundle bundle = sbn.getNotification().extras;

                Log.e("nolistener", "packageName:" + sbn.getPackageName() + "  Notification:" + sbn.getNotification()+ "sbnTag:" + sbn.getTag()  + "extra_text:" + bundle.getCharSequence(Notification.EXTRA_TITLE) + "extra_text:" + bundle.getCharSequence(Notification.EXTRA_TEXT) + ":" + bundle.getCharSequence(Notification.EXTRA_BIG_TEXT) + bundle.getCharSequence(Notification.EXTRA_INFO_TEXT) + bundle.getCharSequence(Notification.EXTRA_SUMMARY_TEXT) + "ticket Text:" + sbn.getNotification().tickerText);// + sbn.getNotification().contentIntent.toString()


                System.out.println("packageName:" + sbn.getPackageName() + "  Notification:" + sbn.getNotification() + sbn.getTag() + sbn.getNotification() + sbn.getNotification() +   "ticket Text:" + sbn.getNotification().tickerText);
            } else {
                Log.e("nolistener", "onNotificationPosted: ");
            }
        }
        if(sbn.getNotification().actions == null){
            Log.e("nolistener", "onNotificationPosted: " );
        }
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        super.onNotificationRemoved(sbn);
    }
}
