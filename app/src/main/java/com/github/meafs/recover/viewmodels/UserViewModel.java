package com.github.meafs.recover.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.github.meafs.recover.api.Repository;
import com.github.meafs.recover.models.CHWModel;

public class UserViewModel extends AndroidViewModel {

    private LiveData<CHWModel> userResponseLiveData;

    public UserViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(String authToken) {
        Repository userRepository = new Repository(authToken);
        userRepository.authUser();
        userResponseLiveData = userRepository.getUserLiveData();
    }

    public LiveData<CHWModel> getuserResponseLiveData() {
        return userResponseLiveData;
    }


}
