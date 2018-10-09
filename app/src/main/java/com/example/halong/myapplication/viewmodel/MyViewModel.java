package com.example.halong.myapplication.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.halong.myapplication.bean.City;
import com.example.halong.myapplication.bean.County;
import com.example.halong.myapplication.bean.Province;
import com.example.halong.myapplication.network.NetUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyViewModel extends AndroidViewModel {
    private MutableLiveData<List<Province>> provinceData = new MutableLiveData<>();

    private MutableLiveData<List<City>> cityData = new MutableLiveData<>();

    private MutableLiveData<List<County>> countyData = new MutableLiveData<>();

    public MyViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<Province>> getProvinceData() {
        return provinceData;
    }

    public MutableLiveData<List<City>> getCityData() {
        return cityData;
    }

    public MutableLiveData<List<County>> getCountyData() {
        return countyData;
    }


    public void requestProvinceData() {
        NetUtil.getWeatherService().getProvinces().enqueue(new Callback<List<Province>>() {
            @Override
            public void onResponse(@NonNull Call<List<Province>> call, @NonNull Response<List<Province>> response) {
                provinceData.postValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<Province>> call, @NonNull Throwable t) {

            }
        });
    }


    public void requestCityData(int province) {
        NetUtil.getWeatherService().getCities(province).enqueue(new Callback<List<City>>() {
            @Override
            public void onResponse(@NonNull Call<List<City>> call, @NonNull Response<List<City>> response) {
                cityData.postValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<City>> call, @NonNull Throwable t) {

            }
        });
    }

    public void requestCountyData(int province, int city) {
        NetUtil.getWeatherService().getCounties(province, city).enqueue(new Callback<List<County>>() {
            @Override
            public void onResponse(@NonNull Call<List<County>> call, @NonNull Response<List<County>> response) {
                countyData.postValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<County>> call, @NonNull Throwable t) {

            }
        });
    }


}
