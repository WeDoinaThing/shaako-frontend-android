package com.github.meafs.recover.activites;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.github.meafs.recover.R;
import com.github.meafs.recover.fragments.ContactFragment;
import com.github.meafs.recover.fragments.PatientFragment;
import com.github.meafs.recover.fragments.UserFragment;
import com.github.meafs.recover.models.LocationData;
import com.github.meafs.recover.viewmodels.MapsViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MapsViewModel mapsViewModel;
    private ArrayList<LocationData> locdata = new ArrayList<>();
    BottomNavigationView bottomNavigation;
    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            item -> {
                bottomNavigation.getMenu().findItem(R.id.user).setIcon(R.drawable.ic_person_inactive);
                bottomNavigation.getMenu().findItem(R.id.patient).setIcon(R.drawable.ic_outline_person_add);
                bottomNavigation.getMenu().findItem(R.id.contact).setIcon(R.drawable.ic_contact);
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

        mapsViewModel = ViewModelProviders.of(this).get(MapsViewModel.class);
        mapsViewModel.init();

        new Handler().postDelayed(() -> {

            mapsViewModel.getLocationResponseLiveData().observe(this, new Observer<List<LocationData>>() {
                @Override
                public void onChanged(List<LocationData> locationData) {
                    locdata.addAll(locationData);
                }
            });

            if (locdata.size() != 0) {
                System.out.println(locdata.get(1).getPlaceName());
            } else {
                Toast.makeText(this, "Error!!", Toast.LENGTH_SHORT).show();
            }
        }, 10000);


        bottomNavigation = findViewById(R.id.bottomNavigationView);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        bottomNavigation.getMenu().findItem(R.id.user).setIcon(R.drawable.ic_baseline_person_24);
        openFragment(UserFragment.newInstance("", ""));
    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.holder, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {

    }
}