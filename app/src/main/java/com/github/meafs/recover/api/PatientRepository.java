package com.github.meafs.recover.api;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.github.meafs.recover.models.TodoModel;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class PatientRepository {

    public static final String Base_URL = "https://jsonplaceholder.typicode.com/todos/";

    private ApiService apiService;
    private MutableLiveData<List<TodoModel>> patientResponceLiveData = new MutableLiveData<>();


    public PatientRepository() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        apiService = new retrofit2.Retrofit.Builder()
                .baseUrl(Base_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService.class);
    }

    public void getPatientData() {
        apiService.getPatientInformation()
                .enqueue(new Callback<List<TodoModel>>() {
                    @Override
                    public void onResponse(Call<List<TodoModel>> call, Response<List<TodoModel>> response) {
                        if (response.body() != null) {
                            patientResponceLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<TodoModel>> call, Throwable t) {
                        patientResponceLiveData.postValue(null);
                    }
                });
    }

    public LiveData<List<TodoModel>> getPatientResponseLiveData() {
//        getPatientData();
        return patientResponceLiveData;
    }

}
