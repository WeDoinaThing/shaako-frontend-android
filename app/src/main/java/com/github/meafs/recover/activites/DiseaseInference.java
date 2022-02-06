package com.github.meafs.recover.activites;

import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.classifier.randomforestclassifier.RandomForestClassifier;
import com.github.meafs.recover.R;
import com.github.meafs.recover.models.Diseases;
import com.github.meafs.recover.models.Symptoms;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DiseaseInference extends AppCompatActivity {
    private static final String TAG = "DiseaseInfActivity";

    private ArrayList<String> sympoms_selection = new ArrayList<>();
    private Map<String, Integer> selectedSymptomMap = new HashMap<>();
    private TextView tvDisease;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_inference);
        tvDisease = findViewById(R.id.disease1_name);

        if(getIntent().hasExtra("SymptomsList"))
            sympoms_selection = getIntent().getStringArrayListExtra("SymptomsList");

        setTag("Selections", sympoms_selection);
        getIdMapOfSymptoms();
        Integer detectedDisease = runInference();
        String diseaseString = Diseases.getDisease(detectedDisease);
        tvDisease.setText(diseaseString);
    }

    private Integer runInference(){
        double[] features = new double[132];
        for (Map.Entry<String, Integer> entry : selectedSymptomMap.entrySet()) {
            features[entry.getValue()] = 1.0;
        }
        RandomForestClassifier randomForestClassifier = new RandomForestClassifier(features);
        return randomForestClassifier.infer();
    }

//    private void addChip(String pItem, ChipGroup pChipGroup) {
//        Chip lChip = new Chip(this);
//        lChip.setText(pItem);
//        lChip.setTextColor(getResources().getColor(R.color.black));
//        lChip.setChipBackgroundColor(getResources().getColorStateList(R.color.azure_maps_marker_blue));
//
//        pChipGroup.addView(lChip, pChipGroup.getChildCount() - 1);
//    }

    private void setTag(String chipName, ArrayList<String> tagList) {
        System.out.println("TTAGLIST: "+ tagList);
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
            chip.setCloseIconResource(R.drawable.next);
            chip.setCloseIconVisible(true);
            //Added click listener on close icon to remove tag from ChipGroup
            chip.setOnCloseIconClickListener(v -> {
                tagList.remove(tagName);
                chipGroup.removeView(chip);
            });

            chipGroup.addView(chip);
        }
    }

    private void getIdMapOfSymptoms() {
        Map<String, Integer> symptomsMap = Symptoms.mapSymptoms();
        for (int i = 0; i< sympoms_selection.size();i++) {
            String symptom = sympoms_selection.get(i).toLowerCase().replace(" ","_");
            Integer symptom_id = symptomsMap.get(symptom);
            selectedSymptomMap.put(symptom, symptom_id);
        }
    }
}