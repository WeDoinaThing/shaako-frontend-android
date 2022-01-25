package com.github.meafs.recover.activites;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.github.meafs.recover.R;
import com.github.meafs.recover.utils.Speak;

import java.util.Locale;

public class SettingActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private SharedPreferences sharedPreferences;
    private EditText editText;
    private String Unit;
    private String Sex;
    private Button saveButton;
    private TextToSpeech engine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        engine = new TextToSpeech(this, this);

        Speak speak = new Speak(SettingActivity.this);

        editText = findViewById(R.id.weightField);
        Spinner spinner1 = (Spinner) findViewById(R.id.unit);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.units, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Unit = String.valueOf(adapterView.getItemAtPosition(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Spinner spinner2 = (Spinner) findViewById(R.id.sex);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.genders, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Sex = String.valueOf(adapterView.getItemAtPosition(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        saveButton = findViewById(R.id.save);

        saveButton.setOnClickListener(view -> {
//            sharedPreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putInt("weight", Integer.parseInt(editText.getText().toString()));
//            editor.putString("sex", Sex);
//            editor.putString("unit", Unit);
//            editor.apply();
//
//            int savedWeight = sharedPreferences.getInt("weight", -1);
//            String savedUnit = sharedPreferences.getString("unit", null);
//            String savedGender = sharedPreferences.getString("sex", null);
//
//            Toast.makeText(SettingActivity.this, "Saved weight: " + savedWeight + " " + savedUnit + " saved gender: " + savedGender, Toast.LENGTH_SHORT).show();
//
            speak.speak(engine, saveButton.getText().toString());
        });


    }

    @Override
    public void onInit(int i) {
        if (i == TextToSpeech.SUCCESS) {
            //Setting speech Language
            engine.setLanguage(Locale.ENGLISH);
            engine.setPitch(1);
        }
    }
}