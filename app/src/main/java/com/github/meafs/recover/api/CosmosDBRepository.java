package com.github.meafs.recover.api;

import static com.github.meafs.recover.utils.Constants.ContainerId;
import static com.github.meafs.recover.utils.Constants.CosmosDBPrimaryKey;
import static com.github.meafs.recover.utils.Constants.CosmosDBURI;
import static com.github.meafs.recover.utils.Constants.DatabaseId;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.github.meafs.recover.models.Document;
import com.github.meafs.recover.models.PatientModel;
import com.google.gson.JsonObject;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class CosmosDBRepository {
    private final MutableLiveData<List<Document>> patientResponceLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> addPatientResponceLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> addDiseaseResponceLiveData = new MutableLiveData<>();
    private final ApiService apiService;
    private String authString;

    public CosmosDBRepository() {


        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        apiService = new retrofit2.Retrofit.Builder()
                .baseUrl(CosmosDBURI)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(ApiService.class);
    }


    public void getPatientDataFromApi() {
        PatientAPI patientAPI = new PatientAPI(DatabaseId, ContainerId, "get");
        try {
            authString = patientAPI
                    .generateAuthHeader(CosmosDBPrimaryKey);
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

    public void postPatientDataFromApi(String partitionSize, JsonObject jsonObject) {
        PatientAPI patientAPI = new PatientAPI(DatabaseId, ContainerId, "post");
        try {
            authString = patientAPI
                    .generateAuthHeader(CosmosDBPrimaryKey);
        } catch (Exception e) {
            e.printStackTrace();
        }

        apiService.addPatient(authString, patientAPI.getDate(), partitionSize, jsonObject).enqueue(new Callback<PatientModel>() {
            @Override
            public void onResponse(Call<PatientModel> call, Response<PatientModel> response) {
                if (response.body() != null && response.code() == 201) {
                    addPatientResponceLiveData.postValue("Done");
                } else {
                    addPatientResponceLiveData.postValue("Failed" + response.code());
                }
            }

            @Override
            public void onFailure(Call<PatientModel> call, Throwable t) {
                addPatientResponceLiveData.postValue("Failed");
            }
        });
    }

    public void addDiseaseScreenData(String partitionKey, String id, JsonObject jsonObject) {
        PatientAPI patientAPI = new PatientAPI(DatabaseId, ContainerId, "patch");
        try {
            authString = patientAPI
                    .generateAuthHeader(CosmosDBPrimaryKey);
        } catch (Exception e) {
            e.printStackTrace();
        }

        apiService.diseaseScreen(authString, patientAPI.getDate(), partitionKey, id, jsonObject).enqueue(new Callback<Document>() {
            @Override
            public void onResponse(Call<Document> call, Response<Document> response) {
                if (response.body() != null && response.code() == 201) {
                    addDiseaseResponceLiveData.postValue("Done");
                } else {
                    addDiseaseResponceLiveData.postValue("Failed");
                }
            }

            @Override
            public void onFailure(Call<Document> call, Throwable t) {
                addDiseaseResponceLiveData.postValue(null);
            }
        });

    }


    public LiveData<List<Document>> getPatientResponseLiveData() {
        return patientResponceLiveData;
    }

    public LiveData<String> addPatientResponseLiveData() {
        return addPatientResponceLiveData;
    }

    public LiveData<String> addDiseaseResponseLiveData() {
        return addDiseaseResponceLiveData;
    }


}
