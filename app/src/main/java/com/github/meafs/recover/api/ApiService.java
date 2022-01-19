package com.github.meafs.recover.api;

import com.github.meafs.recover.models.ContentModel;
import com.github.meafs.recover.models.TodoModel;
import com.github.meafs.recover.models.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET(".")
    Call<List<TodoModel>> getPatientInformation();

    @GET("/ngo_admin/get_content")
    Call<List<ContentModel>> getContent(@Query("token") String authToken);

    @GET("/ngo_admin/verify_access_token")
    Call<UserModel> getUser(@Query("token") String authToken);

}
