package com.github.meafs.recover.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.github.meafs.recover.apipackage.ContentRepository;

import com.github.meafs.recover.models.ContentModel;


import java.util.List;

public class ContentViewModel extends AndroidViewModel {

    private LiveData<List<ContentModel>> contentResponseLiveData;

    public ContentViewModel(@NonNull Application application) {
        super(application);
    }

    public void init() {
        ContentRepository contentRepository = new ContentRepository("blahblahblah");
        contentRepository.getContentData();
        contentResponseLiveData = contentRepository.getContentResponseLiveData();
    }

    public LiveData<List<ContentModel>> getContentResponseLiveData() {
        return contentResponseLiveData;
    }


}
