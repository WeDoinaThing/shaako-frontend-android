package com.github.meafs.recover.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.github.meafs.recover.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SharedPreferences pref = getSharedPreferences("CHW", Context.MODE_PRIVATE);

        if (pref.getString("authToken", "") == null) {

            startActivity(new Intent(SplashActivity.this, WalkThrough.class));

        } else if (pref.getString("authToken", "").length() != 0) {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
        } else {
            startActivity(new Intent(SplashActivity.this, WalkThrough.class));
        }

    }
}