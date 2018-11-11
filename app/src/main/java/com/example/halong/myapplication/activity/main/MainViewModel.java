package com.example.halong.myapplication.activity.main;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import com.example.halong.myapplication.network.NetUtil;
import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends AndroidViewModel {
    private MutableLiveData<String> weatherStringData=new MutableLiveData<>();

    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<String> getWeatherStringData() {
        return weatherStringData;
    }

    public void requestWeatherString(final String weatherId) {
        NetUtil.getWeatherService().getHeWeather(weatherId,NetUtil.Key).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(getApplication());
                try {
                    String s=response.body().string();
                    weatherStringData.setValue(s);
                    sharedPreferences.edit().putString("weatherString",s).apply();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
