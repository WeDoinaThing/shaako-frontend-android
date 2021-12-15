package com.github.meafs.recover.api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Response;

public class ApiResource<T> {

    @NonNull
    private final Status status;

    @Nullable
    private final T data;

    @Nullable
    private final ErrorResponse errorResponse;

    @Nullable
    private final String errorMessage;

    private ApiResource(Status status, @Nullable T data, @Nullable ErrorResponse errorResponse, @Nullable String errorMessage) {
        this.status = status;
        this.data = data;
        this.errorResponse = errorResponse;
        this.errorMessage = errorMessage;
    }

    public static <T> ApiResource<T> create(Response<T> response) {
        if (!response.isSuccessful()) {
            try {
                JSONObject jsonObject = new JSONObject(response.errorBody().string());
                ErrorResponse errorResponse = new Gson()
                        .fromJson(jsonObject.toString(), ErrorResponse.class);
                return new ApiResource<>(Status.ERROR, null, errorResponse, "Something went wrong.");
            } catch (IOException | JSONException e) {
                return new ApiResource<>(Status.ERROR, null, null, "Response Unreachable");
            }
        }
        return new ApiResource<>(Status.SUCCESS, response.body(), null, null);
    }

    public static <T> ApiResource<T> failure(String error) {
        return new ApiResource<>(Status.ERROR, null, null, error);
    }

    public static <T> ApiResource<T> loading() {
        return new ApiResource<>(Status.LOADING, null, null, null);
    }

    @NonNull
    public Status getStatus() {
        return status;
    }

    @Nullable
    public T getData() {
        return data;
    }

    @Nullable
    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }

    @Nullable
    public String getErrorMessage() {
        return errorMessage;
    }
}
