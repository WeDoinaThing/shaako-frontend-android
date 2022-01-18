package com.github.meafs.recover.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.github.meafs.recover.apipackage.PatientRepository;
import com.github.meafs.recover.models.TodoModel;

import java.util.List;

public class ApiViewModel extends AndroidViewModel {

    private LiveData<List<TodoModel>> patientResponseLiveData;

    public ApiViewModel(@NonNull Application application) {
        super(application);
    }

    public void init() {
        PatientRepository patientRepository = new PatientRepository();
        patientRepository.getPatientData();
        patientResponseLiveData = patientRepository.getPatientResponseLiveData();
    }

    public LiveData<List<TodoModel>> getPatientResponseLiveData() {
        return patientResponseLiveData;
    }


}
