package com.github.meafs.recover.api;

import static com.github.meafs.recover.utils.Constants.ContainerId;
import static com.github.meafs.recover.utils.Constants.DatabaseId;

import com.github.meafs.recover.models.AddPatientModel;
import com.github.meafs.recover.models.CHWModel;
import com.github.meafs.recover.models.ContentModel;
import com.github.meafs.recover.models.MapsResultModel;
import com.github.meafs.recover.models.PatientModel;
import com.github.meafs.recover.models.QuizModel;
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

    @GET("/ngo_admin/get_quiz")
    Call<List<QuizModel>> getQuiz(@Query("token") String authToken);

    // Set headers
    @Headers({
            "Accept: application/json",
            "x-ms-version: 2018-12-31",
    })
    @GET("dbs/" + DatabaseId + "/colls/" + ContainerId + "/docs")
    Call<PatientModel> getPatientData(@Header("Authorization") String token, @Header("x-ms-date") String date);

    // For search
    @Headers({
            "Accept: application/json",
            "x-ms-version: 2018-12-31",
    })
    @POST("dbs/" + DatabaseId + "/colls/" + ContainerId + "/docs")
    Call<AddPatientModel> getIndividualPatient(@Header("Authorization") String token, @Header("x-ms-date") String date, @Body String query);

    // For adding new user
    @Headers({
            "Accept: application/json",
            "x-ms-version: 2018-12-31",
    })
    @POST("dbs/" + DatabaseId + "/colls/" + ContainerId + "/docs")
    Call<PatientModel> addPatient(@Header("Authorization") String token, @Header("x-ms-date") String date, @Header("x-ms-documentdb-partitionkey") String partitionKey, @Body JsonObject json);

    @GET("search/poi/json?api-version=1.0&query=hospital&radius=100000")
    Call<MapsResultModel> getHospitals(@Query("subscription-key") String authToken, @Query("lat") String Latitude, @Query("lon") String Longitude);
}
