package com.github.meafs.recover.activites;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.github.meafs.recover.R;
import com.github.meafs.recover.fragments.ContactFragment;
import com.github.meafs.recover.fragments.PatientFragment;
import com.github.meafs.recover.fragments.UserFragment;
import com.github.meafs.recover.models.Document;
import com.github.meafs.recover.models.PatientModel;
import com.github.meafs.recover.viewmodels.PatientViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigation;
    private PatientViewModel patientViewModel;
    private ArrayList<PatientModel> arrayList = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        patientViewModel = ViewModelProviders.of(this).get(PatientViewModel.class);
        patientViewModel.init();

        patientViewModel.getPatientResponseLiveData().observe(this, new Observer<List<Document>>() {
            @Override
            public void onChanged(List<Document> documents) {
                System.out.println(documents.get(1).getWeight());
            }
        });

        bottomNavigation = findViewById(R.id.bottomNavigationView);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        openFragment(UserFragment.newInstance("", ""));
    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.holder, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            item -> {
                switch (item.getItemId()) {
                    case R.id.user:
                        openFragment(UserFragment.newInstance("", ""));
                        return true;
                    case R.id.patient:
                        openFragment(PatientFragment.newInstance("", ""));
                        return true;
                    case R.id.contact:
                        openFragment(ContactFragment.newInstance("", ""));
                        return true;
                }
                return false;
            };

    @Override
    public void onBackPressed() {

    }
}