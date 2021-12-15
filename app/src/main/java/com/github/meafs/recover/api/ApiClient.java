package com.github.meafs.recover.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit;

    private static final String BASE_URL = "https://sandbox.boletobancario.com/api-integration/";

    private static Retrofit getRetrofit(String baseUrl) {

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(90, TimeUnit.SECONDS)
                .readTimeout(90, TimeUnit.SECONDS)
                .writeTimeout(90, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    public static RecoverApi getRecoveryApi() {

        return getRetrofit(BASE_URL).create(RecoverApi.class);

    }
}