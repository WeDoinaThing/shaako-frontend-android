package com.github.meafs.recover.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.github.meafs.recover.api.MapsRepository;
import com.github.meafs.recover.models.LocationData;

import java.util.List;

public class MapsViewModel extends AndroidViewModel {

    private LiveData<List<LocationData>> locationResponseLiveData;

    public MapsViewModel(@NonNull Application application) {
        super(application);
    }

    public void init() {
        MapsRepository repository = new MapsRepository();
        repository.getMapsData();
        locationResponseLiveData = repository.getLocationLiveData();
    }

    public LiveData<List<LocationData>> getLocationResponseLiveData() {
        return locationResponseLiveData;
    }


}
