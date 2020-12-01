package com.example.sqliteproject.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.sqliteproject.R;

public class SplashActivity extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String SWITCH1 = "switch1";

    private boolean switchOnOff;

    private static int Splash_Timer = 4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        loadData();
        if(switchOnOff == true) {
        new Handler().postDelayed(new Runnable () {
            @Override
            public void run() {
                Intent homeintent = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(homeintent);
                finish();
            }
        }, Splash_Timer);}
        else {
            Intent homeintent = new Intent(SplashActivity.this,MainActivity.class);
            startActivity(homeintent);
        }
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        switchOnOff = sharedPreferences.getBoolean(SWITCH1,true);

    }
}