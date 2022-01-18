package com.github.meafs.recover.apipackage;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.github.meafs.recover.models.ContentModel;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContentRepository {

    public static final String Base_URL = "http://192.168.0.105:7000/";

    private ApiService apiService;
    private MutableLiveData<List<ContentModel>> contentResponceLiveData = new MutableLiveData<>();


    public ContentRepository(String authToken) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        apiService = new retrofit2.Retrofit.Builder()
                .baseUrl(Base_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService.class);
    }

    public void getContentData() {

        apiService.getContent()
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

    public LiveData<List<ContentModel>> getContentResponseLiveData() {
        return contentResponceLiveData;
    }
}
