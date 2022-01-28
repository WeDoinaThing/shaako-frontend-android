package com.github.meafs.recover.activites;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.github.meafs.recover.R;
import com.github.meafs.recover.fragments.ContactFragment;
import com.github.meafs.recover.fragments.PatientFragment;
import com.github.meafs.recover.fragments.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
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