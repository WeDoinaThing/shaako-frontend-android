package com.github.meafs.recover.activites;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.meafs.recover.R;
import com.github.meafs.recover.adapters.PatientHistoryAdapter;
import com.github.meafs.recover.utils.Speak;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class PatientDetails extends AppCompatActivity implements TextToSpeech.OnInitListener {

    TextView mIcon;
    TextView mName;
    TextView mArea;
    ImageView mFire;
    TextView mAge;
    TextView mWeight;
    TextView mSex;
    TextView mContact;
    TextView noComorbidity;
    TextView mVisited;
    Button btDiseaseScreen;
    ChipGroup chipGroup;

    RecyclerView rvHistory;
    private MaterialToolbar toolbar;

    private ArrayList<String> comorbidityList;
    private ArrayList<String> historyList;
    private TextToSpeech engine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_details);

        toolbar = findViewById(R.id.toolbar);
        comorbidityList = new ArrayList<>();
        historyList = new ArrayList<>();
        toolbar.setNavigationOnClickListener(view -> {
            super.onBackPressed();
        });

        mIcon = findViewById(R.id.pdIcon);
        mName = findViewById(R.id.pdName);
        mArea = findViewById(R.id.pdArea);
        mFire = findViewById(R.id.pdFire);
        mAge = findViewById(R.id.pdAge);
        mWeight = findViewById(R.id.pdWeight);
        mSex = findViewById(R.id.gender);
        mContact = findViewById(R.id.contact);
        chipGroup = findViewById(R.id.chip_group);
        noComorbidity = findViewById(R.id.pdComorblist);
        mVisited = findViewById(R.id.pdViscount);
        rvHistory = findViewById(R.id.pd_rv_layout);
        btDiseaseScreen = findViewById(R.id.disease_screening);

        engine = new TextToSpeech(PatientDetails.this, this);

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
            mContact.setText(mBundle.getString("contact"));


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

        mContact.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + mContact.getText().toString()));
            startActivity(intent);
        });
        btDiseaseScreen.setOnClickListener(view -> startActivity(new Intent(this, DiseaseScreeningActivity.class)));
        setComorbidityChips();
        setHistoryDetails();
    }

    private void setHistoryDetails() {
        PatientHistoryAdapter patientHistoryAdapter = new PatientHistoryAdapter(historyList, engine);
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
                chip.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Speak speak = new Speak(view.getContext());
                        speak.speak(engine, comorbidity);
                    }
                });
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
        return visited + " times";
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