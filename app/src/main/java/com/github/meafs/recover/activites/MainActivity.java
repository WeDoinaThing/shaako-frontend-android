package com.github.meafs.recover.activites;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;

import com.github.meafs.recover.R;
import com.github.meafs.recover.api.CosmosDBRepository;
import com.github.meafs.recover.fragments.ContactFragment;
import com.github.meafs.recover.fragments.PatientFragment;
import com.github.meafs.recover.fragments.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigation;
    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            item -> {
                bottomNavigation.getMenu().findItem(R.id.user).setIcon(R.drawable.ic_person_inactive);
                bottomNavigation.getMenu().findItem(R.id.patient).setIcon(R.drawable.ic_outline_patient_add);
                bottomNavigation.getMenu().findItem(R.id.contact).setIcon(R.drawable.ic_outline_contact);
                switch (item.getItemId()) {
                    case R.id.user:
                        openFragment(UserFragment.newInstance("", ""));
                        item.setIcon(R.drawable.ic_baseline_person_24);
                        return true;
                    case R.id.patient:
                        openFragment(PatientFragment.newInstance("", ""));
                        item.setIcon(R.drawable.ic_patient);
                        return true;
                    case R.id.contact:
                        openFragment(ContactFragment.newInstance("", ""));
                        item.setIcon(R.drawable.ic_contact);
                        return true;
                }
                return false;
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JsonObject jsonResult = new JsonObject();
        JsonObject InnerJson = new JsonObject();

        InnerJson.addProperty("op", "add");
        InnerJson.addProperty("path", "/comorbidity");
        InnerJson.addProperty("value", "Cancer");

        jsonResult.add("operations", InnerJson);

//        jsonResult.addProperty("operations", " [ { \"op\": \"add\",\"path\": \"/comorbidity\", \"value\": \"555\" } ]");
        CosmosDBRepository cosmosDBRepository = new CosmosDBRepository();
        cosmosDBRepository.addDiseaseScreenData("6", "6", jsonResult);
        cosmosDBRepository.addDiseaseResponseLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {

            }
        });

        bottomNavigation = findViewById(R.id.bottomNavigationView);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        redirect();
    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.holder, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void redirect() {
        if (getIntent().hasExtra("redirect")) {
            if (getIntent().getStringExtra("redirect").equals("Patient")) {
                bottomNavigation.getMenu().findItem(R.id.patient).setIcon(R.drawable.ic_patient);
                openFragment(PatientFragment.newInstance("", ""));
            } else if (getIntent().getStringExtra("redirect").equals("CHW")) {
                bottomNavigation.getMenu().findItem(R.id.user).setIcon(R.drawable.ic_baseline_person_24);
                openFragment(UserFragment.newInstance("", ""));
            } else if (getIntent().getStringExtra("redirect").equals("Contact")) {
                bottomNavigation.getMenu().findItem(R.id.contact).setIcon(R.drawable.ic_contact);
                openFragment(ContactFragment.newInstance("", ""));
            }
        } else {
            bottomNavigation.getMenu().findItem(R.id.user).setIcon(R.drawable.ic_baseline_person_24);
            openFragment(UserFragment.newInstance("", ""));
        }
    }

    @Override
    public void onBackPressed() {

    }
}