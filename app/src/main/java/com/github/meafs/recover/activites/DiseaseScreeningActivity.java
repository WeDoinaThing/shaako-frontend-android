package com.github.meafs.recover.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import com.github.meafs.recover.R;
import com.github.meafs.recover.databinding.ActivityDiseaseScreeningBinding;


public class DiseaseScreeningActivity extends AppCompatActivity {
    private static final String TAG = "ScreeningActivity";

    private ActivityDiseaseScreeningBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_disease_screening);
        binding.setActivityDiseaseScreening(this);

    }
}