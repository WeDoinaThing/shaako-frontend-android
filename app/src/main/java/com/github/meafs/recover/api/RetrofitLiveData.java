package com.github.meafs.recover.api;

import androidx.lifecycle.LiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitLiveData<T> extends LiveData<ApiResource<T>> {

    private Call<T> call;

    public RetrofitLiveData(Call<T> call) {
        this.call = call;
        setValue(ApiResource.loading());
    }

    Callback<T> callback = new Callback<T>() {
        @Override
        public void onResponse(Call<T> call, Response<T> response) {
            setValue(ApiResource.create(response));
        }

        @Override
        public void onFailure(Call<T> call, Throwable t) {
            setValue(ApiResource.failure(t.getMessage()));
        }
    };

    @Override
    protected void onActive() {
        super.onActive();
        call.enqueue(callback);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        if (!hasActiveObservers()) {
            if (!call.isCanceled()) {
                call.cancel();
            }
        }
    }

}