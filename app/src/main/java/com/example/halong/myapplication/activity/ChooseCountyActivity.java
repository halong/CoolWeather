package com.example.halong.myapplication.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.halong.myapplication.R;
import com.example.halong.myapplication.adapter.MyAdapter;
import com.example.halong.myapplication.bean.County;
import com.example.halong.myapplication.utils.WindowUtil;
import com.example.halong.myapplication.viewmodel.MyViewModel;

import java.util.List;

public class ChooseCountyActivity extends AppCompatActivity {
    private ListView mList;
    private List<County> mCounties;
    private MyAdapter myAdapter;

    private MyViewModel myViewModel;

    private int provinceId;
    private int cityId;
    private String cityName;
    private TextView mTitle;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowUtil.setStatusBarTransparent(this);
        setContentView(R.layout.activity_choose_county);

        initData();
        initView();


    }

    private void initData() {
        provinceId = getIntent().getIntExtra("province_id", 0);
        cityId = getIntent().getIntExtra("city_id", 0);
        cityName = getIntent().getStringExtra("city_name");


        myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        myViewModel.requestCountyData(provinceId, cityId);
        myViewModel.getCountyData().observe(this, new Observer<List<County>>() {
            @Override
            public void onChanged(@Nullable List<County> counties) {
                mCounties = counties;
                myAdapter = new MyAdapter(mCounties);
                mList.setAdapter(myAdapter);
            }
        });

    }

    private void initView() {
        mList = (ListView) findViewById(R.id.list);
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ChooseCountyActivity.this, MainActivity.class);
                intent.putExtra("weather_id", mCounties.get(position).getWeather_id());
                startActivity(intent);
            }
        });

        mTitle = (TextView) findViewById(R.id.title);
        mTitle.setText(cityName);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.drawable.back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
