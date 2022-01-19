package com.github.meafs.recover.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.github.meafs.recover.api.Repository;

import com.github.meafs.recover.models.ContentModel;


import java.util.List;

public class ContentViewModel extends AndroidViewModel {

    private LiveData<List<ContentModel>> contentResponseLiveData;

    public ContentViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(String authToken) {
        Repository repository = new Repository(authToken);
        repository.getContentData();
        contentResponseLiveData = repository.getContentResponseLiveData();
    }

    public LiveData<List<ContentModel>> getContentResponseLiveData() {
        return contentResponseLiveData;
    }


}
