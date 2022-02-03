package com.github.meafs.recover.activites;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.github.meafs.recover.R;
import com.github.meafs.recover.models.CHWModel;
import com.github.meafs.recover.utils.Speak;
import com.github.meafs.recover.viewmodels.UserViewModel;
import com.google.zxing.Result;

import java.util.Locale;

public class ScannerActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private CodeScanner mCodeScanner;
    private UserViewModel userViewModel;
    private SharedPreferences sharedPreferences;
    private TextToSpeech engine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
        CodeScannerView scannerView = findViewById(R.id.scanner_view);
        mCodeScanner = new CodeScanner(this, scannerView);

        engine = new TextToSpeech(ScannerActivity.this, this);

        Speak speak = new Speak(ScannerActivity.this);

        userViewModel = ViewModelProviders.of(ScannerActivity.this).get(UserViewModel.class);

        mCodeScanner.startPreview();
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        userViewModel.init(result.getText());
                        userViewModel.getuserResponseLiveData().observe(ScannerActivity.this, new Observer<CHWModel>() {
                                    @Override
                                    public void onChanged(CHWModel chwModel) {
                                        if (chwModel != null && chwModel.getName() != null) {
                                            mCodeScanner.releaseResources();
                                            SaveData(result.getText(), chwModel.getId(), chwModel.getRegion());
                                            startActivity(new Intent(ScannerActivity.this, MainActivity.class));
                                            Toast.makeText(ScannerActivity.this, getResources().getString(R.string.loginSuccessful), Toast.LENGTH_SHORT).show();
                                            speak.speak(engine, getResources().getString(R.string.loginSuccessful));
                                        } else {
                                            mCodeScanner.startPreview();
                                            Toast.makeText(ScannerActivity.this, getResources().getString(R.string.invalidQR), Toast.LENGTH_SHORT).show();
                                            speak.speak(engine, getResources().getString(R.string.invalidQR));
                                        }
                                    }
                                }
                        );
                    }
                });
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        mCodeScanner.startPreview();
    }

    @Override
    protected void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }

    public void SaveData(String token, String chwId, String chwRegion) {
        sharedPreferences = getSharedPreferences("CHW", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("authToken", token);
        editor.putString("chwId", chwId);
        editor.putString("chwRegion", chwRegion);
        editor.apply();

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