package com.github.meafs.recover.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DiseaseScreeningActivity extends AppCompatActivity {
    private static final String TAG = "DiseaseScreeningAct";

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
                    Integer pTB = matchSymptom(symptomsSelected, TuberculosisSymptoms);
                    Integer pML = matchSymptom(symptomsSelected, MalariaSymptoms);
                    if (pTB>pML) {
                        textView.setText("Tuberculosis");
                        System.out.println("Tuber"+pTB+" "+pML);
                    } else if (pTB<pML) {
                        textView.setText("Malaria");
                        System.out.println("Malaria"+pTB+" "+pML);
                    } else {
                        System.out.println("Both"+pTB+" "+pML);
                    }
                } else {
                    Toast.makeText(DiseaseScreeningActivity.this, "Please select at least three symptoms!!", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private Integer matchSymptom(List<Symptopm> selected, List<Symptopm> criteria){
        List<Symptopm> symptopmList = new ArrayList<>(selected);
        symptopmList.retainAll(criteria);
        Log.d(TAG, symptopmList.toString());

        return symptopmList.size();
    }
}