package com.github.meafs.recover.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.github.meafs.recover.api.Repository;
import com.github.meafs.recover.models.QuizModel;

import java.util.List;

public class QuizViewModel extends AndroidViewModel {

    private LiveData<List<QuizModel>> quizModelLiveData;

    public QuizViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(String authToken) {
        Repository userRepository = new Repository(authToken);
        userRepository.getQuizData();
        quizModelLiveData = userRepository.getQuizList();
    }

    public LiveData<List<QuizModel>> getQuizResponseLiveData() {
        return quizModelLiveData;
    }


}
