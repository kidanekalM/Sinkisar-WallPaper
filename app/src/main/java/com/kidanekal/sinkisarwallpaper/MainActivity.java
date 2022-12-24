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
import android.icu.util.TimeZone;
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
                /*
                long diff = 968112000000L % AlarmManager.INTERVAL_DAY;
                long diff2 = 968176800000L % AlarmManager.INTERVAL_DAY;
                long today = System.currentTimeMillis()%AlarmManager.INTERVAL_DAY;
                //today = today/AlarmManager.INTERVAL_HOUR;
                long remain = AlarmManager.INTERVAL_DAY - ( System.currentTimeMillis() % AlarmManager.INTERVAL_DAY );
                remain /= AlarmManager.INTERVAL_HOUR;
                today /= AlarmManager.INTERVAL_HOUR;
                */
                Toast.makeText(c,  " ዛሬ ቀኑ "+GetEthiopianDate()+" ነው", Toast.LENGTH_LONG).show();

            }
        };
        registerReceiver(br, new IntentFilter("com.kidanekal.sinksarwallpaper") );

        PendingIntent changeWallPaper = PendingIntent.getBroadcast( this, 0, new Intent("com.kidanekal.sinksarwallpaper"),0);
        AlarmManager alarmManager =(AlarmManager) getSystemService(ALARM_SERVICE);

        long startingTime = AlarmManager.INTERVAL_DAY - ( System.currentTimeMillis() % AlarmManager.INTERVAL_DAY );
        alarmManager.setInexactRepeating(AlarmManager.RTC, System.currentTimeMillis()+startingTime,AlarmManager.INTERVAL_DAY,changeWallPaper);
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
    long GetEthiopianDate()
    {
        /** long sep11 = 21772800000L == Meskerem 1*/
        long sep10 = 21853347000L;

        /** 1972 January 1*/
        long leapYr = 63152547000L;

        /** One Year is 365.25 days */
        long oneYear = 31557600000L;

        /** 937088547000 = 6 972080547000 = 10 968192547000 = 30 System.currentTimeMillis(); */
        long today = System.currentTimeMillis();

        long TodayInEthiopia  = ((today - sep10) % oneYear)/ AlarmManager.INTERVAL_DAY;

        if (TodayInEthiopia == 0)
        {
            if ( ( (today-leapYr) / oneYear ) % 4 == 3)
                return 6;
            else
                return 5;
        }
        else if (TodayInEthiopia > 360)
        {
            return TodayInEthiopia - 360;
        }
        else
        {
            TodayInEthiopia %= 30;

            if (TodayInEthiopia == 0)
                return 30;

            else
                return TodayInEthiopia;
        }
    }

}