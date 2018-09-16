package com.example.halong.myapplication.network;

import com.example.halong.myapplication.bean.City;
import com.example.halong.myapplication.bean.County;
import com.example.halong.myapplication.bean.HeWeather;
import com.example.halong.myapplication.bean.Province;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WeatherService {    //interface must be public
    @GET("weather")
    Call<HeWeather> getHeWeather(@Query("cityid") String cityid, @Query("key") String key);

    @GET("china")
    Call<List<Province>> getProvinces();

    @GET("china/{city}")
    Call<List<City>> getCities(@Path("city") int city);

    @GET("china/{city}/{county}")
    Call<List<County>> getCounties(@Path("city") int city, @Path("county") int county);

    @GET("bing_pic")
    Call<ResponseBody> getBingPic();
}
