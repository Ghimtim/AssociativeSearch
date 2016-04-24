package com.jnu.associativesearch.dao;

import com.jnu.associativesearch.beans.MediaFile;

/**
 * Created by zhuang on 2016/3/21.
 */
public interface MediaDao {

    public MediaFile getMediaByName(String name);
}
