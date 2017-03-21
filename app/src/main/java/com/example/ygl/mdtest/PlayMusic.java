package com.example.ygl.mdtest;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by YGL on 2017/3/21.
 */

public class PlayMusic extends Service {
    private MediaPlayer mediaPlayer;

    @Override
    public void onCreate(){
        super.onCreate();
        mediaPlayer=MediaPlayer.create(this,R.raw.twentyoneguns);
    }

    @Override
    public void onDestroy(){
        Log.i("xxx","Service onDestroy");
        super.onDestroy();
        mediaPlayer.release();
        stopSelf();
    }

    @Override
    public IBinder onBind(Intent intent){
        return null;
    }

    @Override
    public int onStartCommand(Intent intent,int flags,int startId){
        Log.i("xxx","Service onStartCommand");
        mediaPlayer.start();
        return super.onStartCommand(intent,flags,startId);
    }
}
