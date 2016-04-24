package com.example.zhuang.helloworld.dao;

import android.test.AndroidTestCase;
import android.util.Log;

import com.jnu.associativesearch.beans.MediaFile;
import com.jnu.associativesearch.dao.MediaDao;
import com.jnu.associativesearch.dao.MediaDaoImpl;

/**
 * Created by zhuang on 2016/3/22.
 */
public class MediaDaoTest extends AndroidTestCase {

    public void testGetMediaByName() throws Exception {

        MediaDao mediaDao = new MediaDaoImpl(this.getContext());
        MediaFile mf = mediaDao.getMediaByName("Promise");
        Log.e("Music", mf.toString());

       // mediaDao.getMediaByName();

    }
}