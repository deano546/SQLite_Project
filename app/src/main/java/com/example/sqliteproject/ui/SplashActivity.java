package com.example.sqliteproject.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.sqliteproject.R;

public class SplashActivity extends AppCompatActivity {

    private static int Splash_Timer = 4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable () {
            @Override
            public void run() {
                Intent homeintent = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(homeintent);
                finish();
            }
        }, Splash_Timer);
    }
}