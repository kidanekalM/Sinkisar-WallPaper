package com.kidanekal.sinkisarwallpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.WallpaperManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BroadcastReceiver br = new BroadcastReceiver() {
            @Override
            public void onReceive(Context c, Intent i) {
                ChangeWallPaper();
                Toast.makeText(c, "Change", Toast.LENGTH_LONG).show();
            }
        };
        registerReceiver(br, new IntentFilter("com.kidanekal.sinksarwallpaper") );

        PendingIntent changeWallPaper = PendingIntent.getBroadcast( this, 0, new Intent("com.kidanekal.sinksarwallpaper"),0);

        AlarmManager alarmManager =(AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setInexactRepeating(AlarmManager.RTC, 0,AlarmManager.INTERVAL_DAY,changeWallPaper);
    }
    // TODO ChangeWallpaper
    void ChangeWallPaper()
    {
        final WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
        try {
            // set the wallpaper by calling the setResource function and
            // passing the drawable file
            // todo Get by id
            long number = GetEthiopianDate();
            String name = "day"+number;
            int id = getResources().getIdentifier(name,"drawable", getApplication().getPackageName());

            wallpaperManager.setResource(+ id);
        }
        catch (IOException e) {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
            //e.printStackTrace();
        }
    }
    // TODO GetEthiopianDate
    long GetEthiopianDate()
    {
        Date TodayInMills =  new Date();
        long September11 =  21859200000L / AlarmManager.INTERVAL_DAY;
        long Today = TodayInMills.getTime() / AlarmManager.INTERVAL_DAY;
        long TodayInEthiopia = ( Today - September11 ) / 30;

        if (TodayInEthiopia == 0)
        {
            return 30;
        }
        else
        {
            return TodayInEthiopia;
        }
    }

}