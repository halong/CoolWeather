package com.example.halong.myapplication.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * http工具：下载api
 * eg:http://guolin.tech/api/weather/register   key=7bb09ce0d85147c1b06df360258eacd7
 * eg:http://guolin.tech/api/weather?cityid=CN101190401&key=7bb09ce0d85147c1b06df360258eacd7
 */
public class NetUtil {
    private static Retrofit.Builder mRetrofitBuilder;
    private static Retrofit WeatherRetrofit;
    private static WeatherService WeatherService;

    private static final String WeatherBaseUrl = "http://guolin.tech/api/";
    public static final String Key="7bb09ce0d85147c1b06df360258eacd7";

    private NetUtil() {
    }

    private static Retrofit.Builder getRetrofitBuilder() {
        if (mRetrofitBuilder == null) {
            mRetrofitBuilder = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create());
        }
        return mRetrofitBuilder;
    }

    private static Retrofit getWeatherRetrofit() {
        if (WeatherRetrofit == null) {
            WeatherRetrofit = getRetrofitBuilder()
                    .baseUrl(WeatherBaseUrl)
                    .build();
        }
        return WeatherRetrofit;
    }


    public static WeatherService getWeatherService(){
        if (WeatherService == null) {
            WeatherService =getWeatherRetrofit().create(WeatherService.class);
        }
        return WeatherService;
    }
}
