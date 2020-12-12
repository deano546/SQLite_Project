package com.example.sqliteproject.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.example.sqliteproject.R;

public class PreferenceActivity extends AppCompatActivity {

    //Shared Preferences code adapted from https://www.youtube.com/watch?v=fJEFZ6EOM9o

    Switch swPref;
    Button btnSavePref;
    Button btnBack;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String SWITCH1 = "switch1";

    private boolean switchOnOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);

        swPref = findViewById(R.id.swPref);
        btnSavePref = findViewById(R.id.btnSavePref);
        btnBack = findViewById(R.id.btnBack);

        btnSavePref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
                Toast.makeText(PreferenceActivity.this, "Preference Saved", Toast.LENGTH_SHORT).show();
            }
        });

        loadData();
        updateViews();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PreferenceActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });


    }

    //Committing new preference
    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean(SWITCH1, swPref.isChecked());
        editor.apply();
    }

    //Check current preference
    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        switchOnOff = sharedPreferences.getBoolean(SWITCH1,true);

    }

    //Set the switch on or off, depending on what was loaded in the previous method
    public void updateViews() {
        swPref.setChecked(switchOnOff);
    }
}