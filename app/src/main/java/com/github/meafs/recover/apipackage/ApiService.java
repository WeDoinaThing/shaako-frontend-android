package com.github.meafs.recover.apipackage;

import com.github.meafs.recover.models.TodoModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("")
    Call<List<TodoModel>> getPatientInformation();
}
