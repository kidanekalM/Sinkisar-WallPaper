package com.kidanekal.sinkisarwallpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.WallpaperManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BroadcastReceiver br = new BroadcastReceiver() {
            @Override
            public void onReceive(Context c, Intent i) {
                Toast.makeText(c, "A toast!", Toast.LENGTH_LONG).show();
            }
        };
        registerReceiver(br, new IntentFilter("com.kidanekal.sinksarwallpaper") );

        PendingIntent changeWallPaper = PendingIntent.getBroadcast( this, 0, new Intent("com.kidanekal.sinksarwallpaper"),0);

        AlarmManager alarmManager =(AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setInexactRepeating(AlarmManager.RTC, 0,100,changeWallPaper);
    }
    // TODO ChangeWallpaper
    void ChangeWallPaper()
    {
        final WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
        try {
            // set the wallpaper by calling the setResource function and
            // passing the drawable file
            // todo
            long day = GetEthiopianDate();
            String picture = "Day"+day+".jpg";
            wallpaperManager.setResource(+ R.drawable.ic_launcher_background);
        } catch (IOException e) {
            // here the errors can be logged instead of printStackTrace
            e.printStackTrace();
        }
    }
    // TODO GetEthiopianDate
    long GetEthiopianDate()
    {
        return 1;
    }

}