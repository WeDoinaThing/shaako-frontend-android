package com.github.meafs.recover.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.github.meafs.recover.api.CosmosDBRepository;
import com.github.meafs.recover.models.Document;

import java.util.List;


public class PatientViewModel extends AndroidViewModel {

    private LiveData<List<Document>> patientResponseLiveData;

    public PatientViewModel(@NonNull Application application) {
        super(application);
    }

    public void init() {
        CosmosDBRepository repository = new CosmosDBRepository();
        repository.getPatientDataFromApi();
        patientResponseLiveData = repository.getPatientResponseLiveData();
    }

    public LiveData<List<Document>> getPatientResponseLiveData() {
        return patientResponseLiveData;
    }


}
