package com.github.meafs.recover.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.github.meafs.recover.R;
import com.github.meafs.recover.models.CHWModel;
import com.github.meafs.recover.viewmodels.UserViewModel;
import com.google.zxing.Result;

public class ScannerActivity extends AppCompatActivity {

    private CodeScanner mCodeScanner;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
        CodeScannerView scannerView = findViewById(R.id.scanner_view);
        mCodeScanner = new CodeScanner(this, scannerView);


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
                                        if (chwModel != null) {
                                            System.out.println(chwModel.getName());
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
}