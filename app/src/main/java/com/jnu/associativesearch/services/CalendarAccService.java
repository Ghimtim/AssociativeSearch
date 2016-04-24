package com.jnu.associativesearch.services;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

import com.jnu.associativesearch.beans.RelatedRecord;
import com.jnu.associativesearch.dao.RecordDao;
import com.jnu.associativesearch.dao.RecordDaoImpl;

import java.util.Date;

/**
 * Created by GoneAir on 2016/3/23.
 */
public class CalendarAccService extends AccessibilityService {
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

        if(event.getEventType() == AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED){

            if(event.getClassName().equals("android.app.Notification") && event.getPackageName().equals("com.android.calendar")){
                RelatedRecord record = new RelatedRecord();
                RecordDao rd = new RecordDaoImpl(getApplicationContext());
                String text = event.getText().toString();
                int idx = text.indexOf("-");
                Log.i("AccessServiceTest", text + idx);
                String name;
                String addr;
                if(idx != -1) {
                    name = text.substring(1, idx - 1);
                    addr = text.substring(idx + 1, text.length() - 1);
                }else{
                    name = text.substring(1,text.length() - 1);
                    addr = "";
                }
                Date systime = new Date();
                long timestamp = systime.getTime();
                record.setName(name);
                record.setPath(addr);
                record.setStart_time(timestamp);
                record.setEnd_time(timestamp);
                record.setMime("application/calendar");

                rd.insertRecord(record);


                //record.setName(event.);
            }
        }

    }

    @Override
    public void onInterrupt() {

    }

    @Override
    public void onServiceConnected() {
        Log.i("AccessServiceTest", "onServiceConnected");
        AccessibilityServiceInfo info = new AccessibilityServiceInfo();
        info.eventTypes = AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED;
        info.notificationTimeout = 100;
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_ALL_MASK;
        setServiceInfo(info);
    }

}
