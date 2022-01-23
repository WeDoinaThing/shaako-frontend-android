package com.github.meafs.recover.api;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.github.meafs.recover.models.Document;
import com.github.meafs.recover.models.PatientModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class CosmosDBRepository {
    public static final String DB_URI = "https://timaginecup-test.documents.azure.com/";

    private ApiService apiService;
    private final MutableLiveData<List<Document>> patientResponceLiveData = new MutableLiveData<>();
    private String authString;

    public CosmosDBRepository() {

        apiService = new retrofit2.Retrofit.Builder()
                .baseUrl(DB_URI)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService.class);
    }


    public void getPatientDataFromApi() {
        PatientAPI patientAPI = new PatientAPI("imadatabaseid", "imcontainerid", "get");
        try {
            authString = patientAPI
                    .generateAuthHeader("xwu06IODbACkMxfVePHQ4j1JZIXp1gAXO9JS622VLFwYuV1VWq96HgesTIVzW1YRteRnz8TEbQIjaCzfhr3MFA==");
        } catch (Exception e) {
            e.printStackTrace();
        }

        apiService.getPatientData(authString, patientAPI.getDate())
                .enqueue(new Callback<PatientModel>() {

                    @Override
                    public void onResponse(Call<PatientModel> call, Response<PatientModel> response) {
                        if (response.body() != null) {
                            patientResponceLiveData.postValue(response.body().getDocuments());
                        }
                    }

                    @Override
                    public void onFailure(Call<PatientModel> call, Throwable t) {
                        patientResponceLiveData.postValue(null);
                    }
                });
    }


    public LiveData<List<Document>> getPatientResponseLiveData() {
        return patientResponceLiveData;
    }

}
