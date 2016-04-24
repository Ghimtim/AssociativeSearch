package com.jnu.associativesearch.activities;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jnu.associativesearch.beans.RelatedRecord;
import com.jnu.associativesearch.broadcast.MusicReceiver;
import com.jnu.associativesearch.broadcast.WPSReceiver;
import com.jnu.associativesearch.dao.RecordDao;
import com.jnu.associativesearch.dao.RecordDaoImpl;
import com.jnu.associativesearch.R;
import com.jnu.associativesearch.services.TaskMonitor;

import java.util.Iterator;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements ServiceConnection{

    private BroadcastReceiver wpsReceiver;

    private BroadcastReceiver smsReceiver;

    private BroadcastReceiver callReceiver;

    private BroadcastReceiver musicReceiver;

    private Intent taskMService;

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search);
        ViewGroup view = (ViewGroup)findViewById(R.id.search_main);

        while(view != null){
            Log.e("view", "onCreate: " + view.getClass().toString() );
            view = (ViewGroup)view.getParent();
        }




//sms receiver
     /*   SmsReceiver smsReceiver = new SmsReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(smsReceiver, intentFilter);*/

//wps receiver
        if (wpsReceiver == null) {

            wpsReceiver = new WPSReceiver();

            IntentFilter wpsIF = new IntentFilter();

            wpsIF.addAction("cn.wps.moffice.file.close");
            wpsIF.addAction("com.kingsoft.writer.back.key.down");
            wpsIF.addAction("com.kingsoft.writer.home.key.down");
            wpsIF.addAction("cn.wps.moffice.file.save");

            registerReceiver(wpsReceiver, wpsIF);


        //call receiver
           // BroadcastReceiver callReceiver = new CallReceiver();

            if(musicReceiver == null){
                musicReceiver = new MusicReceiver();
                IntentFilter musicIF = new IntentFilter();
/*        iF.addAction("com.android.music.playstatechanged");
        iF.addAction("com.android.music.playbackcomplete");
        iF.addAction("com.android.music.queuechanged");*/
                musicIF.addAction("com.android.music.metachanged");


                musicIF.addAction("com.kugou.android.music.metachanged");
                // iF.addAction("com.kugou.android.music.playstatechanged");
    /*    iF.addAction("com.netease.cloudmusic.service.PlayService");
        iF.addAction("com.netease.music.action.STAR_MUSIC");
        iF.addAction("com.netease.cloudmusic.metachanged");*/

                musicIF.addAction("com.android.mediacenter.metachanged");
                registerReceiver(musicReceiver,musicIF);
            }



            RecordDao dao = new RecordDaoImpl(this);
            List<RelatedRecord> records = dao.getAllRecords();

            Iterator<RelatedRecord> iterator = records.iterator();
            while(iterator.hasNext()){
                Log.e("all", "onCreate: " + iterator.next().toString() );
            }

            if(taskMService == null){

                taskMService = new Intent(this,TaskMonitor.class);

                bindService(taskMService,this, Context.BIND_AUTO_CREATE);
            }


         /*  String name = records.get(0).getName();
            if (name != null) {
                Log.e("wps", "onCreate: " + records.get(0).toString());
            }else{
                Log.e("wps", "onCreate: name is null" );
            }*/

        }
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {

    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }


  /*  @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode == event.KEYCODE_BACK){
            moveTaskToBack(true);
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(taskMService != null){
            unbindService(this);
        }

        if(smsReceiver != null){
            unregisterReceiver(smsReceiver);
        }

        if(wpsReceiver != null){
            unregisterReceiver(wpsReceiver);
        }

        if(musicReceiver != null){
            unregisterReceiver(musicReceiver);
        }
    }


}
