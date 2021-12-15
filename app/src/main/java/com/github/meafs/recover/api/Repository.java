package com.github.meafs.recover.api;

import androidx.lifecycle.LiveData;

import java.util.Map;

public class Repository {

    public LiveData<ApiResource<ApiResponse>> getResponse(Map<String, String> headers) {
        return new RetrofitLiveData<>(ApiClient.getRecoveryApi().getRecoveryData(headers));
    }

}