package com.github.meafs.recover.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.meafs.recover.R;
import com.github.meafs.recover.databinding.ActivityDiseaseScreeningBinding;
import com.github.meafs.recover.models.Symptopm;
import com.pchmn.materialchips.ChipsInput;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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