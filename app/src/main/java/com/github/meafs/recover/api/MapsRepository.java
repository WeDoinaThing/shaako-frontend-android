package com.github.meafs.recover.api;

import static com.github.meafs.recover.utils.Constants.AzureMapsToken;
import static com.github.meafs.recover.utils.Constants.AzureMapsURI;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.github.meafs.recover.models.LocationData;
import com.github.meafs.recover.models.MapsResultModel;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapsRepository {

    private final ApiService apiService;
    private final MutableLiveData<List<LocationData>> locationLiveDataList = new MutableLiveData<>();
    private final MutableLiveData<LocationData> individualLocation = new MutableLiveData<>();
    private final List<LocationData> locationDataList = new ArrayList<>();


    public MapsRepository() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        apiService = new retrofit2.Retrofit.Builder()
                .baseUrl(AzureMapsURI)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(ApiService.class);
    }

    public void getMapsData(String lat, String lon) {

        // Provide lat and long from user location
        apiService.getHospitals(AzureMapsToken, lat, lon).enqueue(new Callback<MapsResultModel>() {
            @Override
            public void onResponse(Call<MapsResultModel> call, Response<MapsResultModel> response) {
                if (response.body() != null) {
                    for (int i = 0; i < response.body().getResults().size(); i++) {
                        locationDataList.add(new LocationData(response.body().getResults().get(i).getPoi().getName(),
                                response.body().getResults().get(i).getPosition().getLat().toString(),
                                response.body().getResults().get(i).getPosition().getLon().toString()));
                    }
                    locationLiveDataList.postValue(locationDataList);
                } else {
                    locationLiveDataList.postValue(null);
                }


            }

            @Override
            public void onFailure(Call<MapsResultModel> call, Throwable t) {
                System.out.println("Error");
                locationLiveDataList.postValue(null);
            }
        });

    }

    public LiveData<List<LocationData>> getLocationLiveData() {
        return locationLiveDataList;
    }

}
