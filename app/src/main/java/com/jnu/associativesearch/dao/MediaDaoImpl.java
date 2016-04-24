package com.jnu.associativesearch.dao;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import com.jnu.associativesearch.beans.MediaFile;

/**
 * Created by zhuang on 2016/3/21.
 */
public class MediaDaoImpl implements MediaDao{

    private Context context;

    @Override
    public MediaFile getMediaByName(String name) {
        ContentResolver resolver = context.getContentResolver();

        Cursor cursor = resolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, new String[]{MediaStore.Audio.Media.TITLE, MediaStore.Audio.Media.DATA, MediaStore.Audio.Media.DISPLAY_NAME, MediaStore.Audio.Media.MIME_TYPE}, "title like ?", new String[]{"%"+name+"%"}, null);

        MediaFile mediaFile = new MediaFile();

        String title;

        String displayName;
        String data;
        String mime;
        if(cursor != null){
            cursor.moveToNext();
            title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));
            displayName = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME));
            data = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
            mime = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.MIME_TYPE));
            mediaFile.setName(title);
            mediaFile.setDisplayName(displayName);
            mediaFile.setPath(data);
            mediaFile.setMime(mime);
        }
        return mediaFile;
    }

    public MediaDaoImpl(Context context) {
        this.context = context;
    }
}
