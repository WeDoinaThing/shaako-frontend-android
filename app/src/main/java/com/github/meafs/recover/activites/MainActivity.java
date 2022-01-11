package com.github.meafs.recover.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.github.meafs.recover.R;
import com.github.meafs.recover.fragments.ContactFragment;
import com.github.meafs.recover.fragments.PatientFragment;
import com.github.meafs.recover.fragments.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
}