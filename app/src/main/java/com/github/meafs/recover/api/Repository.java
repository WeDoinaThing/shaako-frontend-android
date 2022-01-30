package com.github.meafs.recover.api;

import static com.github.meafs.recover.utils.Constants.BackendURI;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.github.meafs.recover.models.CHWModel;
import com.github.meafs.recover.models.ContentModel;
import com.github.meafs.recover.models.QuizModel;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {

    private final MutableLiveData<List<ContentModel>> contentResponceLiveData = new MutableLiveData<>();
    private final MutableLiveData<CHWModel> userResponceLiveData = new MutableLiveData();
    private final MutableLiveData<List<QuizModel>> quizResponceLiveData = new MutableLiveData();
    private final ApiService apiService;
    private final String authtoken;


    public Repository(String authToken) {
        authtoken = authToken;
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        apiService = new retrofit2.Retrofit.Builder()
                .baseUrl(BackendURI)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(ApiService.class);
    }

    public void getContentData() {

        System.out.println(authtoken);
        apiService.getContent(authtoken)
                .enqueue(new Callback<List<ContentModel>>() {
                    @Override
                    public void onResponse(Call<List<ContentModel>> call, Response<List<ContentModel>> response) {
                        if (response.body() != null) {
                            contentResponceLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<ContentModel>> call, Throwable t) {
                        contentResponceLiveData.postValue(null);
                    }
                });
    }


    public void authUser() {

        apiService.getUser(authtoken)
                .enqueue(new Callback<CHWModel>() {
                    @Override
                    public void onResponse(Call<CHWModel> call, Response<CHWModel> response) {
                        if (response.body() != null && response.code() == 200) {
                            userResponceLiveData.postValue(response.body());
                        } else {
                            userResponceLiveData.postValue(null);
                        }
                    }

                    @Override
                    public void onFailure(Call<CHWModel> call, Throwable t) {
                        userResponceLiveData.postValue(null);
                    }
                });
    }

    public void getQuizData() {
        apiService.getQuiz(authtoken).enqueue(new Callback<List<QuizModel>>() {
            @Override
            public void onResponse(Call<List<QuizModel>> call, Response<List<QuizModel>> response) {
                if (response.body() != null && response.code() == 200) {
                    quizResponceLiveData.postValue(response.body());
                    System.out.println(response.body());
                } else {
                    quizResponceLiveData.postValue(null);
                    System.out.println("Null");
                }
            }

            @Override
            public void onFailure(Call<List<QuizModel>> call, Throwable t) {
                quizResponceLiveData.postValue(null);
                System.out.println("Failed");
            }
        });
    }


    public LiveData<List<ContentModel>> getContentResponseLiveData() {
        return contentResponceLiveData;
    }

    public LiveData<CHWModel> getUserLiveData() {
        return userResponceLiveData;
    }

    public LiveData<List<QuizModel>> getQuizList() {
        return quizResponceLiveData;
    }
}
