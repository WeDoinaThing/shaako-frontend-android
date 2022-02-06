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
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DiseaseInference extends AppCompatActivity {
    private static final String TAG = "DiseaseInfActivity";

    private ArrayList<String> sympoms_selection = new ArrayList<>();
    private Map<String, Integer> selectedSymptomMap = new HashMap<>();
    private TextView tvDisease_1;
    private TextView tvDisease_2;
    private TextView tvDisease_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_inference);
        tvDisease_1 = findViewById(R.id.disease1_name);
        tvDisease_2 = findViewById(R.id.disease2_name);
        tvDisease_3 = findViewById(R.id.disease3_name);

        if(getIntent().hasExtra("SymptomsList"))
            sympoms_selection = getIntent().getStringArrayListExtra("SymptomsList");

        setTag("Selections", sympoms_selection);
        getIdMapOfSymptoms();
        runInference();
    }

    private void runInference(){
        double[] features = new double[132];
        for (Map.Entry<String, Integer> entry : selectedSymptomMap.entrySet()) {
            features[entry.getValue()] = 1.0;
        }
        RandomForestClassifier randomForestClassifier = new RandomForestClassifier(features);
        int[] predictions = randomForestClassifier.infer();
        int[] predictionIndexes = getBestKIndices(predictions, 3);

        tvDisease_1.setText(Diseases.getDisease(predictionIndexes[0]));
        tvDisease_2.setText(Diseases.getDisease(predictionIndexes[1]));
        tvDisease_3.setText(Diseases.getDisease(predictionIndexes[2]));
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

    private int[] getBestKIndices(int[] array, int num) {
        IndexValuePair[] pairs = new IndexValuePair[array.length];
        for (int i = 0; i < array.length; i++) {
            pairs[i] = new IndexValuePair(i, array[i]);
        }
        Arrays.sort(pairs, (o1, o2) -> Integer.compare(o2.value, o1.value));
        int[] result = new int[num];
        for (int i = 0; i < num; i++) {
            result[i] = pairs[i].index;
        }
        return result;
    }
    private static class IndexValuePair {
        private int index;
        private int value;

        public IndexValuePair(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

}