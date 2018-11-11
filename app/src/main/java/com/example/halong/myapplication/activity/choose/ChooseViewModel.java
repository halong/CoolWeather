package com.example.halong.myapplication.activity.choose;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.halong.myapplication.bean.City;
import com.example.halong.myapplication.bean.County;
import com.example.halong.myapplication.bean.Province;
import com.example.halong.myapplication.database.MyDataBase;
import com.example.halong.myapplication.network.NetUtil;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChooseViewModel extends AndroidViewModel {
    public ChooseViewModel(@NonNull Application application) {
        super(application);
    }


    public LiveData<List<Province>> getProvinceData() {
        return MyDataBase.getInstance(getApplication()).provinceDao().getAll();
    }

    public LiveData<List<City>> getCityData(int provinceId) {
        return MyDataBase.getInstance(getApplication()).cityDao().getCitiesByProvinceId(provinceId);
    }

    public LiveData<List<County>> getCountyData(int cityId) {
        return MyDataBase.getInstance(getApplication()).countyDao().getCountiesByCityId(cityId);
    }


    public void requestProvinceData() {
        NetUtil.getWeatherService().getProvinces().enqueue(new Callback<List<Province>>() {
            @Override
            public void onResponse(@NonNull Call<List<Province>> call, @NonNull Response<List<Province>> response) {
                for (Province province :
                        response.body()) {
                    MyDataBase.getInstance(getApplication()).provinceDao().addItem(province);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Province>> call, @NonNull Throwable t) {

            }
        });
    }


    public void requestCityData(final int province) {
        NetUtil.getWeatherService().getCities(province).enqueue(new Callback<List<City>>() {
            @Override
            public void onResponse(@NonNull Call<List<City>> call, @NonNull Response<List<City>> response) {
                for (City city :
                        response.body()) {
                    city.setProvinceId(province);
                    MyDataBase.getInstance(getApplication()).cityDao().addItem(city);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<City>> call, @NonNull Throwable t) {

            }
        });
    }

    public void requestCountyData(int province, final int city) {
        NetUtil.getWeatherService().getCounties(province, city).enqueue(new Callback<List<County>>() {
            @Override
            public void onResponse(@NonNull Call<List<County>> call, @NonNull Response<List<County>> response) {
                for (County county :
                        response.body()) {
                    county.setCityId(city);
                    MyDataBase.getInstance(getApplication()).countyDao().addItem(county);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<County>> call, @NonNull Throwable t) {

            }
        });
    }



}
