package com.github.meafs.recover.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.meafs.recover.R;
import com.github.meafs.recover.models.Symptopm;
import com.pchmn.materialchips.ChipsInput;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiseaseScreeningActivity extends AppCompatActivity {

    private Button button;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_screening);

        ChipsInput chipsInput = (ChipsInput) findViewById(R.id.chips_input);

        button = findViewById(R.id.checkBtn);
        textView = findViewById(R.id.diseaseName);
//        textView.setVisibility(View.GONE);

        List<Symptopm> symptopmArrayList = new ArrayList<>();
        symptopmArrayList.add(new Symptopm("Headache"));
        symptopmArrayList.add(new Symptopm("Fever"));
        symptopmArrayList.add(new Symptopm("Chest pain"));
        symptopmArrayList.add(new Symptopm("Chills"));
        symptopmArrayList.add(new Symptopm("Muscle pain"));
        symptopmArrayList.add(new Symptopm("Flu"));
        symptopmArrayList.add(new Symptopm("Diarrhea"));

        chipsInput.setFilterableList(symptopmArrayList);

        List<Symptopm> TuberculosisSymptoms = new ArrayList<>();
        TuberculosisSymptoms.add(new Symptopm("Headache"));
        TuberculosisSymptoms.add(new Symptopm("Fever"));
        TuberculosisSymptoms.add(new Symptopm("Chest pain"));

        List<Symptopm> MalariaSymptoms = new ArrayList<>();
        MalariaSymptoms.add(new Symptopm("Muscle pain"));
        MalariaSymptoms.add(new Symptopm("Flu"));
        MalariaSymptoms.add(new Symptopm("Diarrhea"));


        List<Symptopm> symptomsSelected = (List<Symptopm>) chipsInput.getSelectedChipList();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (symptomsSelected.size() >= 3) {
                    if (haveSameElements(TuberculosisSymptoms, symptomsSelected)) {
//                        textView.setVisibility(View.VISIBLE);
                        textView.setText("Tuberculosis");
                        System.out.println("Tuber");

                    } else if (haveSameElements(MalariaSymptoms, symptomsSelected)) {
//                        textView.setVisibility(View.VISIBLE);
                        textView.setText("Malaria");
                        System.out.println("Malaria");
                    } else {
                        System.out.println("Else");
                    }

//                    Toast.makeText(DiseaseScreeningActivity.this, symptomsSelected.get(0).getLabel(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DiseaseScreeningActivity.this, "Please select at least three symptoms!!", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


    public static <T> boolean haveSameElements(Collection<T> col1, Collection<T> col2) {
        if (col1 == col2)
            return true;

        // If either list is null, return whether the other is empty
        if (col1 == null)
            return col2.isEmpty();
        if (col2 == null)
            return col1.isEmpty();

        // If lengths are not equal, they can't possibly match
        if (col1.size() != col2.size())
            return false;

        // Helper class, so we don't have to do a whole lot of autoboxing
        class Count {
            // Initialize as 1, as we would increment it anyway
            public int count = 1;
        }

        final Map<T, Count> counts = new HashMap<>();

        // Count the items in col1
        for (final T item : col1) {
            final Count count = counts.get(item);
            if (count != null)
                count.count++;
            else
                // If the map doesn't contain the item, put a new count
                counts.put(item, new Count());
        }

        // Subtract the count of items in col2
        for (final T item : col2) {
            final Count count = counts.get(item);
            // If the map doesn't contain the item, or the count is already reduced to 0, the lists are unequal
            if (count == null || count.count == 0)
                return false;
            count.count--;
        }

        // At this point, both collections are equal.
        // Both have the same length, and for any counter to be unequal to zero, there would have to be an element in col2 which is not in col1, but this is checked in the second loop, as @holger pointed out.
        return true;
    }
}