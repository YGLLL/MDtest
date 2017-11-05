package com.example.ygl.mdtest;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import java.sql.Time;
import java.util.Calendar;
import java.util.Random;
import java.util.Timer;

/**
 * Created by YGL on 2017/3/21.
 */

public class MusicService extends Service {
    private MediaPlayer mediaPlayer;
    private static final String TAG = "PlayMusic";
    @Override
    public void onCreate(){
        super.onCreate();
        Calendar calendar = Calendar.getInstance();//获取系统时间
        Random random=new Random(calendar.get(Calendar.SECOND));//使用当前秒数作为随机源
        switch (random.nextInt(3)){
            case 0:
                mediaPlayer=MediaPlayer.create(this,R.raw.twentyoneguns);
                break;
            case 1:
                mediaPlayer=MediaPlayer.create(this,R.raw.autobots);
                break;
            case 2:
                mediaPlayer=MediaPlayer.create(this,R.raw.arrivaltoearth);
                break;
        }
    }

    @Override
    public void onDestroy(){
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
        mediaPlayer.start();
        return super.onStartCommand(intent,flags,startId);
    }
}
