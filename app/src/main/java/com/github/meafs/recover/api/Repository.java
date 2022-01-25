package com.github.meafs.recover.api;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.github.meafs.recover.models.CHWModel;
import com.github.meafs.recover.models.ContentModel;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {

    public static final String Base_URL = "http://194.163.148.43/";
    private final MutableLiveData<List<ContentModel>> contentResponceLiveData = new MutableLiveData<>();
    private final MutableLiveData<CHWModel> userResponceLiveData = new MutableLiveData();
    private ApiService apiService;
    private String authtoken;


    public Repository(String authToken) {
        authtoken = authToken;
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        apiService = new retrofit2.Retrofit.Builder()
                .baseUrl(Base_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(ApiService.class);
    }

    public void getContentData() {

        System.out.println(authtoken);
        apiService.getContent(authtoken)
                .enqueue(new Callback<List<ContentModel>>() {
                    @Override
                    public void onResponse(Call<List<ContentModel>> call, Response<List<ContentModel>> response) {
                        if (response.body() != null) {
                            contentResponceLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<ContentModel>> call, Throwable t) {
                        contentResponceLiveData.postValue(null);
                    }
                });
    }


    public void authUser() {

        apiService.getUser(authtoken)
                .enqueue(new Callback<CHWModel>() {
                    @Override
                    public void onResponse(Call<CHWModel> call, Response<CHWModel> response) {
                        if (response.body() != null && response.code() == 200) {
                            userResponceLiveData.postValue(response.body());
                        } else {
                            userResponceLiveData.postValue(null);
                        }
                    }

                    @Override
                    public void onFailure(Call<CHWModel> call, Throwable t) {
                        userResponceLiveData.postValue(null);
                    }
                });
    }

    public void getPatientData() {

    }


    public LiveData<List<ContentModel>> getContentResponseLiveData() {
        return contentResponceLiveData;
    }

    public LiveData<CHWModel> getUserLiveData() {
        return userResponceLiveData;
    }
}
