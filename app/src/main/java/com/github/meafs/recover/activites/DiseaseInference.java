package com.github.meafs.recover.activites;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.classifier.randomforestclassifier.RandomForestClassifier;
import com.github.meafs.recover.R;
import com.github.meafs.recover.models.DiseaseInformation;
import com.github.meafs.recover.models.Diseases;
import com.github.meafs.recover.models.Symptoms;
import com.github.meafs.recover.utils.IntegerUtility;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DiseaseInference extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "DiseaseInfActivity";
    Map<String, Integer> symptomsMap = Symptoms.mapSymptoms();

    private ArrayList<String> sympoms_selection = new ArrayList<>();
    private final Map<String, Integer> selectedSymptomMap = new HashMap<>();
    private MaterialToolbar toolbar;
    private TextView tvDisease_1;
    private TextView tvDisease_1_info;
    private ConstraintLayout diag_card;
    private CardView disease_info_card;
    private CardView disease_doctor_card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_inference);
        toolbar = findViewById(R.id.toolbar);
        diag_card = findViewById(R.id.diagnosis_card);
        tvDisease_1 = findViewById(R.id.disease_name);
        disease_info_card = findViewById(R.id.disease_information_card);
        tvDisease_1_info = findViewById(R.id.disease_information);
        disease_doctor_card = findViewById(R.id.disease_doctor_card);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        if (getIntent().hasExtra("SymptomsList"))
            sympoms_selection = getIntent().getStringArrayListExtra("SymptomsList");

        setTag("Selections", sympoms_selection);
        getIdMapOfSymptoms(symptomsMap, sympoms_selection);
        runInference(selectedSymptomMap);
        diag_card.setOnClickListener(this);
    }
    @Override
    public void onBackPressed() {
        ((Activity) this.getApplicationContext()).finish();

    }
    private void setTag(String chipName, ArrayList<String> tagList) {
        System.out.println("TTAGLIST: " + tagList);
        final ChipGroup chipGroup = findViewById(R.id.symptoms_chipGroup);
        for (int index = 0; index < tagList.size(); index++) {
            final String tagName = tagList.get(index);
            final Chip chip = new Chip(this);
            int paddingDp = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 10,
                    getResources().getDisplayMetrics()
            );
            chip.setPadding(paddingDp, paddingDp, paddingDp, paddingDp);
            chip.setText(tagName);
            chip.setCloseIconResource(R.drawable.ic_baseline_close_24);
            chip.setCloseIconVisible(true);
            chip.setChipBackgroundColor(ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), R.color.lightSecondaryColor)));
            //Added click listener on close icon to remove tag from ChipGroup
            chip.setOnCloseIconClickListener(v -> {
                if (tagList.size() >3) {
                    tagList.remove(tagName);
                    chipGroup.removeView(chip);
                    selectedSymptomMap.remove(tagName.toLowerCase().replace(" ", "_"));
//                    System.out.println("tagName: " + tagName);
//                    System.out.println("sympomappu: " + selectedSymptomMap.toString());
                    runInference(selectedSymptomMap);
                }
                else {
                    Toast.makeText(this.getApplicationContext(), "Patient cannot have less than 3 symptoms for a proper prediction.", Toast.LENGTH_SHORT).show();
                }
            });

            chipGroup.addView(chip);
        }
    }

    private void runInference(Map<String, Integer> sympMap ) {
        double[] features = new double[132];
        for (Map.Entry<String, Integer> entry : sympMap.entrySet()) {
            features[entry.getValue()] = 1.0;
        }
        RandomForestClassifier randomForestClassifier = new RandomForestClassifier(features);
        int[] predictions = randomForestClassifier.infer();
        int[] predictionIndexes = IntegerUtility.getBestKIndices(predictions, 3);

        tvDisease_1.setText(Diseases.getDisease(predictionIndexes[0]));
        tvDisease_1_info.setText(DiseaseInformation.getTB());
    }

    private void getIdMapOfSymptoms(Map<String, Integer> received_symptomsMap, ArrayList <String> received_sympoms_selection) {
        for (int i = 0; i < received_sympoms_selection.size(); i++) {
            String symptom = received_sympoms_selection.get(i).toLowerCase().replace(" ", "_");
            Integer symptom_id = received_symptomsMap.get(symptom);
            selectedSymptomMap.put(symptom, symptom_id);
        }
    }

    @Override
    public void onClick(View view) {
        if (disease_info_card.getVisibility() == View.VISIBLE) {
            disease_info_card.setVisibility(View.GONE);
            disease_doctor_card.setVisibility(View.GONE);
        } else {
            disease_info_card.setVisibility(View.VISIBLE);
            disease_doctor_card.setVisibility(View.VISIBLE);
        }
    }
}