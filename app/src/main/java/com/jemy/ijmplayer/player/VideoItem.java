package com.jemy.ijmplayer.player;

import android.util.Log;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


public class VideoItem implements Serializable {
    private String mName;
    private String mSize;
    private String mTime;
    private String mVideoPath;
    private String mCoverPath;

    public static final String VIDEO_PATH_PREFIX = "http://cdn.iorange.vip/";
    public static final String COVER_PATH_PREFIX = "http://demo-videos.qnsdk.com/snapshoot/";
    public static final String COVER_PATH_SUFFIX = ".jpg";
    public void setName(String name) {
        this.mVideoPath = VIDEO_PATH_PREFIX + name;
        Log.e("zw","mVideoPath:"+mVideoPath);
        this.mName = getVideoName(name);
        this.mCoverPath = "http://cdn.iorange.vip/53391a1d0bccaf12434297c5e950ff2a.jpg";
    }

    public void setSize(String size) {
        this.mSize = size;
    }

    public void setTime(String time) {
        time = time.substring(0, 13);
        mTime = stampToDate(time);
    }

    public String getName() {
        return mName;
    }

    public String getSize() {
        return mSize;
    }

    public String getTime() {
        return mTime;
    }

    public String getVideoPath() {
        return mVideoPath;
    }

    public String getCoverPath() {
        return mCoverPath;
    }

    private static String stampToDate(String s) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        return simpleDateFormat.format(date);
    }

    private static String getVideoName(String name) {
        int start = name.lastIndexOf("/");
        if (start != -1) {
            String subString = name.substring(start + 1);
            subString = subString.replace(".mp4", "");
            return subString;
        } else {
            return null;
        }
    }


}
