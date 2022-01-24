package com.github.meafs.recover.api;

import com.github.meafs.recover.models.AddPatientModel;
import com.github.meafs.recover.models.CHWModel;
import com.github.meafs.recover.models.ContentModel;
import com.github.meafs.recover.models.PatientModel;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @GET("/ngo_admin/get_content")
    Call<List<ContentModel>> getContent(@Query("token") String authToken);

    @GET("/ngo_admin/verify_access_token")
    Call<CHWModel> getUser(@Query("token") String authToken);

    // Set headers
    @Headers({
            "Accept: application/json",
            "x-ms-version: 2018-12-31",
    })
    @GET("dbs/imadatabaseid/colls/imcontainerid/docs")
    Call<PatientModel> getPatientData(@Header("Authorization") String token, @Header("x-ms-date") String date);

    // For search
    @Headers({
            "Accept: application/json",
            "x-ms-version: 2018-12-31",
    })
    @POST("dbs/imadatabaseid/colls/imcontainerid/docs")
    Call<AddPatientModel> getIndividualPatient(@Header("Authorization") String token, @Header("x-ms-date") String date, @Body String query);

    // For adding new user
    @Headers({
            "Accept: application/json",
            "x-ms-version: 2018-12-31",
//            "Content-Type: application/json"
    })
    @POST("dbs/newdatabaseid/colls/newcontainerid/docs")
    Call<PatientModel> addPatient(@Header("Authorization") String token, @Header("x-ms-date") String date, @Header("x-ms-documentdb-partitionkey") String partitionKey, @Body JsonObject json);
}
