package com.github.meafs.recover.activites;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Build;
import android.os.Bundle;

import com.github.meafs.recover.R;
import com.github.meafs.recover.api.ApiService;
import com.github.meafs.recover.api.PatientAPI;
import com.github.meafs.recover.fragments.ContactFragment;
import com.github.meafs.recover.fragments.PatientFragment;
import com.github.meafs.recover.fragments.UserFragment;
import com.github.meafs.recover.models.PatientModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigation;
    private ApiService apiService;
    private String authString;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        Test test = new Test("xwu06IODbACkMxfVePHQ4j1JZIXp1gAXO9JS622VLFwYuV1VWq96HgesTIVzW1YRteRnz8TEbQIjaCzfhr3MFA==",
//                "imadatabaseid", "imcontainerid");
//        System.out.println("Main Activity: " + test.encodeURI());

        PatientAPI patientAPI = new PatientAPI("imadatabaseid", "imcontainerid", "get");
        try {
            authString = patientAPI.getAuthenticationString();
            System.out.println(authString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient defaultHttpClient = new OkHttpClient.Builder()
                .addInterceptor(
                        new Interceptor() {
                            @Override
                            public okhttp3.Response intercept(Chain chain) throws IOException {
                                Request request = chain.request().newBuilder()
                                        .addHeader("Authorization", authString).addHeader("x-ms-date", patientAPI.getDate()).build();
                                return chain.proceed(request);
                            }
                        }).addInterceptor(interceptor).build();

        apiService = new retrofit2.Retrofit.Builder()
                .baseUrl("https://timaginecup-test.documents.azure.com:443/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(defaultHttpClient)
                .build()
                .create(ApiService.class);

        apiService.getPatientData()
                .enqueue(new Callback<List<PatientModel>>() {
                    @Override
                    public void onResponse(Call<List<PatientModel>> call, Response<List<PatientModel>> response) {
                        if (response.body() != null) {
                            System.out.println(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<PatientModel>> call, Throwable t) {
                        System.out.println("Error!!");
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
}