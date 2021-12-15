package com.github.meafs.recover.api;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;

public interface RecoverApi {

    @GET("data")
    Call<ApiResponse> getRecoveryData(@HeaderMap Map<String, String> headers);

}