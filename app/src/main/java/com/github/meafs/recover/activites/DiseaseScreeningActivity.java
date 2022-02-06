package com.github.meafs.recover.activites;


import android.content.Context;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.meafs.recover.R;
import com.github.meafs.recover.adapters.SymptomsAdapter;
import com.github.meafs.recover.models.SymptomCategories;
import com.github.meafs.recover.models.SymptomChild;
import com.github.meafs.recover.models.Symptoms;

import java.util.ArrayList;

public class DiseaseScreeningActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private Context mContext;
    private Button floatingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_screening);
        mContext = DiseaseScreeningActivity.this;
        mRecyclerView = findViewById(R.id.recyclerView);
        floatingButton = findViewById(R.id.disease_selection_done);
        SymptomsAdapter recyclerDataAdapter = new SymptomsAdapter(getSymptoms(), floatingButton);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(recyclerDataAdapter);
        mRecyclerView.setHasFixedSize(true);
    }

    private ArrayList<SymptomCategories> getSymptoms() {
        ArrayList<SymptomCategories> arrDummyData = new ArrayList<>();
        ArrayList<SymptomChild> childDataItems;
        ArrayList<String> throatANdChest = Symptoms.getThroatAndChest();
        ArrayList<String> skinAndNails = Symptoms.getSkinAndNails();
        ArrayList<String> stomachAndAppetite = Symptoms.getStomachAndAppetite();
        ArrayList<String> face = Symptoms.getFace();
        ArrayList<String> muscleLimbsBonesVessels = Symptoms.getMuscleLimbsBonesVessels();
        ArrayList<String> excretions = Symptoms.getExcretion();
        ArrayList<String> riskFactors = Symptoms.getRiskFactors();
        ArrayList<String> mental = Symptoms.getMental();
        ArrayList<String> others = Symptoms.getOthers();

        childDataItems = new ArrayList<>();

        for (int i = 0; i < throatANdChest.size(); i++) {
            String text = throatANdChest.get(i).toUpperCase().replace("_", " ").split("\\.")[0];
            childDataItems.add(new SymptomChild(text));
        }
        arrDummyData.add(new SymptomCategories("Throat and Chest", R.drawable.throatandchest, childDataItems));

        childDataItems = new ArrayList<>();
        for (int i = 0; i < skinAndNails.size(); i++) {
            String text = skinAndNails.get(i).toUpperCase().replace("_", " ").split("\\.")[0];
            childDataItems.add(new SymptomChild(text));
        }
        arrDummyData.add(new SymptomCategories("Skin and Nails", R.drawable.rash, childDataItems));

        childDataItems = new ArrayList<>();
        for (int i = 0; i < stomachAndAppetite.size(); i++) {
            String text = stomachAndAppetite.get(i).toUpperCase().replace("_", " ").split("\\.")[0];
            childDataItems.add(new SymptomChild(text));
        }
        arrDummyData.add(new SymptomCategories("Stomach and Appetite", R.drawable.digestion, childDataItems));

        childDataItems = new ArrayList<>();
        for (int i = 0; i < face.size(); i++) {
            String text = face.get(i).toUpperCase().replace("_", " ").split("\\.")[0];
            childDataItems.add(new SymptomChild(text));
        }
        arrDummyData.add(new SymptomCategories("Facial Features", R.drawable.freckles, childDataItems));

        childDataItems = new ArrayList<>();
        for (int i = 0; i < muscleLimbsBonesVessels.size(); i++) {
            String text = muscleLimbsBonesVessels.get(i).toUpperCase().replace("_", " ").split("\\.")[0];
            childDataItems.add(new SymptomChild(text));
        }
        arrDummyData.add(new SymptomCategories("Muscles, Limbs, Bones and Vessels", R.drawable.muscle, childDataItems));

        childDataItems = new ArrayList<>();
        for (int i = 0; i < excretions.size(); i++) {
            String text = excretions.get(i).toUpperCase().replace("_", " ").split("\\.")[0];
            childDataItems.add(new SymptomChild(text));
        }
        arrDummyData.add(new SymptomCategories("Excretion Related", R.drawable.kidney, childDataItems));

        childDataItems = new ArrayList<>();
        for (int i = 0; i < riskFactors.size(); i++) {
            String text = riskFactors.get(i).toUpperCase().replace("_", " ").split("\\.")[0];
            childDataItems.add(new SymptomChild(text));
        }
        arrDummyData.add(new SymptomCategories("Risk Factors", R.drawable.folder, childDataItems));

        childDataItems = new ArrayList<>();
        for (int i = 0; i < mental.size(); i++) {
            String text = mental.get(i).toUpperCase().replace("_", " ").split("\\.")[0];
            childDataItems.add(new SymptomChild(text));
        }
        arrDummyData.add(new SymptomCategories("Psychological Symptoms", R.drawable.mood, childDataItems));

        childDataItems = new ArrayList<>();
        for (int i = 0; i < others.size(); i++) {
            String text = others.get(i).toUpperCase().replace("_", " ").split("\\.")[0];
            childDataItems.add(new SymptomChild(text));
        }
        arrDummyData.add(new SymptomCategories("Other External Symptoms", R.drawable.sick, childDataItems));
        return arrDummyData;
    }
}
