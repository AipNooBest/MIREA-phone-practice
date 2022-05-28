package ru.mirea.danilov.mireaproject.ui.mediaplayer;

import static android.content.ContentValues.TAG;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import ru.mirea.danilov.mireaproject.R;

public class PlayerService extends Service {
    private MediaPlayer mediaPlayer;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate(){
        Log.d(TAG, "onCreate: PlayerService created!");
        mediaPlayer = MediaPlayer.create(this, R.raw.last_remote);
        mediaPlayer.setLooping(true);
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        mediaPlayer.start();
        Log.d(TAG, "onStartCommand: Service started!");
        return START_STICKY;
    }
    @Override
    public void onDestroy() {
        mediaPlayer.stop();
    }
}