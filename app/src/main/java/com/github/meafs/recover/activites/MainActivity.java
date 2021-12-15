package com.github.meafs.recover.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.meafs.recover.R;
import com.github.meafs.recover.utils.Alert;

public class MainActivity extends AppCompatActivity {

    private Alert alert;
    private Button button;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        alert = new Alert(this, "Hello","Ok","Back");
//        alert.CreateAlert().show();
        button = findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SettingActivity.class));
            }
        });

        Button button2 = findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    sharedPreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);

                    int savedWeight = sharedPreferences.getInt("weight", -1);
                    String savedUnit = sharedPreferences.getString("unit", null);
                    String savedGender = sharedPreferences.getString("sex", null);

                    Toast.makeText(MainActivity.this, "Saved weight: " + savedWeight + " " + savedUnit + " saved gender: " + savedGender, Toast.LENGTH_SHORT).show();
                } catch (Exception ex) {
                    Toast.makeText(MainActivity.this, "Null", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}