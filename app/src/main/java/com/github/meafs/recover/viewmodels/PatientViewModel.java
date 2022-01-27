package com.github.meafs.recover.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.github.meafs.recover.api.CosmosDBRepository;
import com.github.meafs.recover.models.Document;
import com.google.gson.JsonObject;

import java.util.List;


public class PatientViewModel extends AndroidViewModel {

    private LiveData<List<Document>> patientResponseLiveData;
    private final CosmosDBRepository repository = new CosmosDBRepository();
    private LiveData<String> liveData;

    public PatientViewModel(@NonNull Application application) {
        super(application);
    }

    public void init() {
        repository.getPatientDataFromApi();
        patientResponseLiveData = repository.getPatientResponseLiveData();
    }

    public void addPatient(String size, JsonObject jsonObject) {
        repository.postPatientDataFromApi("[" + "\"" + (Integer.parseInt(size) + 1) + "\"" + "]", jsonObject);
        liveData = repository.addPatientResponseLiveData();
    }

    public LiveData<List<Document>> getPatientResponseLiveData() {
        return patientResponseLiveData;
    }

    public LiveData<String> addPatientDone() {
        return liveData;
    }

}
