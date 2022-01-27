package com.github.meafs.recover.activites;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.github.meafs.recover.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        SharedPreferences pref = getSharedPreferences("CHW", Context.MODE_PRIVATE);

        if (pref.getString("authToken", "") == null) {
//            startActivity(new Intent(SplashActivity.this, OnBoardingActivity.class));
            transitionToActivity(OnBoardingActivity.class);
        } else if (pref.getString("authToken", "").length() != 0) {
//            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            transitionToActivity(MainActivity.class);
        } else {
//            startActivity(new Intent(SplashActivity.this, OnBoardingActivity.class));
            transitionToActivity(OnBoardingActivity.class);
        }

    }

    public void transitionToActivity(Class classFile) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), classFile));
            }
        }, 1000);
    }

}