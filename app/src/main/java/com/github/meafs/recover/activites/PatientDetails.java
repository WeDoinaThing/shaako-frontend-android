package com.github.meafs.recover.activites;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.meafs.recover.R;
import com.github.meafs.recover.adapters.PatientHistoryAdapter;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class PatientDetails extends AppCompatActivity {

    TextView mIcon;
    TextView mName;
    TextView mArea;
    ImageView mFire;
    TextView mAge;
    TextView mWeight;
    TextView mSex;
    TextView noComorbidity;
    TextView mVisited;
    ChipGroup chipGroup;

    RecyclerView rvHistory;

    private ArrayList<String> comorbidityList;
    private ArrayList<String> historyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_details);

        comorbidityList = new ArrayList<>();
        historyList = new ArrayList<>();

        mIcon = findViewById(R.id.pdIcon);
        mName = findViewById(R.id.pdName);
        mArea = findViewById(R.id.pdArea);
        mFire = findViewById(R.id.pdFire);
        mAge = findViewById(R.id.pdAge);
        mWeight = findViewById(R.id.pdWeight);
        mSex = findViewById(R.id.gender);
        chipGroup = findViewById(R.id.chip_group);
        noComorbidity = findViewById(R.id.pdComorblist);
        mVisited = findViewById(R.id.pdViscount);
        rvHistory = findViewById(R.id.pd_rv_layout);

        mVisited.setText(getVisitedText(20));

        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {
            mIcon.setText(mBundle.getString("icon"));
            ((GradientDrawable) mIcon.getBackground()).setColor(mBundle.getInt("colorIcon"));
            mName.setText(mBundle.getString("name"));
            mArea.setText(mBundle.getString("area"));
            mAge.setText(String.valueOf(getAge(mBundle.getString("age"))));
            mWeight.setText(mBundle.getString("weight"));
            mSex.setText(getFirstLetter(mBundle.getString("sex")));

            String comorbidities = mBundle.getString("comorbidity");
            if (!comorbidities.isEmpty())
                comorbidityList.addAll(Arrays.asList(comorbidities.split(";")));

            String histories = mBundle.getString("history");
            if (!histories.isEmpty())
                historyList.addAll(Arrays.asList(histories.split(";")));
        }
        mFire.setOnClickListener(view -> {
            if (mFire.getColorFilter() != null) {
                mFire.clearColorFilter();
            } else {
                mFire.setColorFilter(ContextCompat.getColor(PatientDetails.this,
                        R.color.colorGreen));
            }
        });

        setComorbidityChips();
        setHistoryDetails();
    }

    private void setHistoryDetails(){
        PatientHistoryAdapter patientHistoryAdapter = new PatientHistoryAdapter(historyList);
        rvHistory.setHasFixedSize(true);
        rvHistory.setLayoutManager(new LinearLayoutManager(this));
        rvHistory.setAdapter(patientHistoryAdapter);
    }

    private void setComorbidityChips() {
        if (!comorbidityList.isEmpty()) {
            for (String comorbidity : comorbidityList) {
                Chip chip = new Chip(this);
                chip.setText(comorbidity);
                chip.setTextColor(Color.parseColor("#000000"));
                chip.setTextSize(16.0f);
                chipGroup.addView(chip);
            }
        } else {
            noComorbidity.setVisibility(View.VISIBLE);
        }
    }


    private int getAge(String dobString) {

        Date date = null;
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            date = sdf.parse(dobString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (date == null) return 0;

        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.setTime(date);

        int year = dob.get(Calendar.YEAR);
        int month = dob.get(Calendar.MONTH);
        int day = dob.get(Calendar.DAY_OF_MONTH);

        dob.set(year, month + 1, day);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }


        return age;
    }

    public String getFirstLetter(String s) {
        if (s.equals("Male")) {
            return "Male";
        } else if (s.equals("Female")) {
            return "Female";
        } else {
            return "Other";
        }
    }

    private String getVisitedText(int visited) {
        return String.valueOf(visited) + " times";
    }

}