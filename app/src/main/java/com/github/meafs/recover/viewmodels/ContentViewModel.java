package com.github.meafs.recover.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.github.meafs.recover.api.ContentRepository;

import com.github.meafs.recover.models.ContentModel;


import java.util.List;

public class ContentViewModel extends AndroidViewModel {

    private LiveData<List<ContentModel>> contentResponseLiveData;

    public ContentViewModel(@NonNull Application application) {
        super(application);
    }

    public void init() {
        ContentRepository contentRepository = new ContentRepository("aa53d4f90f93e71f6d858301ab0ef53f");
        contentRepository.getContentData();
        contentResponseLiveData = contentRepository.getContentResponseLiveData();
    }

    public LiveData<List<ContentModel>> getContentResponseLiveData() {
        return contentResponseLiveData;
    }


}
