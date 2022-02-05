package com.github.meafs.recover.activites;

import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.github.meafs.recover.R;
import com.github.meafs.recover.models.Symptoms;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DiseaseInference extends AppCompatActivity {
    ArrayList<String> sympoms_selection = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_inference);
        this.sympoms_selection = getIntent().getStringArrayListExtra("SymptomsList");
        setTag("Selections", sympoms_selection);
        Log.i("SYMPT", this.sympoms_selection.toString());
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
            chip.setOnCloseIconClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tagList.remove(tagName);
                    chipGroup.removeView(chip);
                }
            });

            chipGroup.addView(chip);
        }
    }

    private Map<String, String> getIDfromDiseaseName(ArrayList<String> sympoms_selection) {
        ArrayList<String> all_symptoms = Symptoms.getAll_symptoms();
        Map <String, String> selection_map = new HashMap<String, String>();
        for (int i = 0; i< sympoms_selection.size();i++) {
            String symptom = sympoms_selection.get(i).toLowerCase().replace(" ","_");
            int pos = all_symptoms.indexOf(symptom);
            String symptom_id = all_symptoms.get(pos).split("\\.")[1] ;
            selection_map.put(symptom, symptom_id);
        }

        return selection_map;
    }
}