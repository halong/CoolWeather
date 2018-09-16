package com.example.halong.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.halong.myapplication.bean.City;
import com.example.halong.myapplication.bean.HeWeather;
import com.example.halong.myapplication.bean.Province;
import com.example.halong.myapplication.database.MyDataBase;
import com.example.halong.myapplication.network.RetrofitUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";

    private Button mButton;
    private TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


    }

    private void initView() {
        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(this);
        mText = (TextView) findViewById(R.id.text);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.button:
                RetrofitUtil.getWeatherService().getHeWeather("CN101190401",RetrofitUtil.Key).enqueue(new Callback<HeWeather>() {
                    @Override
                    public void onResponse(Call<HeWeather> call, Response<HeWeather> response) {

                        HeWeather.HeWeatherBean heWeatherBean=response.body().getHeWeather().get(0);
                        HeWeather.HeWeatherBean.NowBean nowBean=heWeatherBean.getNow();

                        mText.setText(nowBean.getCond_txt()+"--"+nowBean.getWind_dir());
                    }

                    @Override
                    public void onFailure(Call<HeWeather> call, Throwable t) {
                        Log.d(TAG,t.getMessage());
                    }
                });

//                RetrofitUtil.getApiService().getProvinces().enqueue(new Callback<List<Province>>() {
//                    @Override
//                    public void onResponse(Call<List<Province>> call, Response<List<Province>> response) {
//                        for (Province province :
//                                response.body()) {
//                            Log.d(TAG, province.getName());
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<Province>> call, Throwable t) {
//                        Log.d(TAG,t.getMessage());
//                    }
//                });

//                Province province1 = new Province();
//                province1.setId(1);
//                province1.setName("dsashd中文fsdfsdf");
//                MyDataBase.getInstance().provinceDao().addItem(province1);

//                MyDataBase.getInstance().provinceDao().updateItem(province1);

//                List<Province> provinces =  MyDataBase.getInstance().provinceDao().getAll();
//                StringBuilder builder=new StringBuilder();
//                for (Province province : provinces) {
//                    builder.append(province.getId()+"--"+province.getName()+"\n");
//                }
//                mText.setText(builder.toString());

//                Province province=MyDataBase.getInstance().provinceDao().getProvinceById(1);
//                mText.setText(province.toString());

//                City city=new City();
//                MyDataBase.getInstance().cityDao().addItem();
//                List<City> cities = MyDataBase.getInstance().cityDao().getAll();
//                mText.setText(cities.size()+" ");



                break;
        }
    }
}
