package com.github.meafs.recover.api;

import com.github.meafs.recover.models.CHWModel;
import com.github.meafs.recover.models.ContentModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("/ngo_admin/get_content")
    Call<List<ContentModel>> getContent(@Query("token") String authToken);

    @GET("/ngo_admin/verify_access_token")
    Call<CHWModel> getUser(@Query("token") String authToken);

}
