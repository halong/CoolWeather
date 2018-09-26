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

public class ChooseViewModel extends AndroidViewModel {
    private MutableLiveData<List<Province>> provinceLiveData=new MutableLiveData<>();
    private MutableLiveData<List<City>> cityLiveData=new MutableLiveData<>();
    private MutableLiveData<List<County>> countyLiveData=new MutableLiveData<>();

    public ChooseViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<Province>> getProvinceLiveData() {
        return provinceLiveData;
    }

    public MutableLiveData<List<City>> getCityLiveData() {
        return cityLiveData;
    }

    public MutableLiveData<List<County>> getCountyLiveData() {
        return countyLiveData;
    }

    public void requestProvinceData(){
        NetUtil.getWeatherService().getProvinces().enqueue(new Callback<List<Province>>() {
            @Override
            public void onResponse(Call<List<Province>> call, Response<List<Province>> response) {
                provinceLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Province>> call, Throwable t) {

            }
        });

    }


}
