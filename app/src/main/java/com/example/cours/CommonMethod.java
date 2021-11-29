package com.example.cours;

import android.content.Context;
import android.media.MediaPlayer;
import android.provider.MediaStore;

public class CommonMethod {
    public static MediaPlayer player;
    public static void  SoundPlayer(Context ctx,int raw_id){
        player = MediaPlayer.create(ctx, raw_id);
        player.setLooping(true);
        player.setVolume(100, 100);

        player.start();
    }
}
