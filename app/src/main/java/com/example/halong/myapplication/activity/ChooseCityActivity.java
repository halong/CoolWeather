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
import com.example.halong.myapplication.bean.City;
import com.example.halong.myapplication.utils.WindowUtil;
import com.example.halong.myapplication.viewmodel.MyViewModel;

import java.util.List;

public class ChooseCityActivity extends AppCompatActivity {

    private MyViewModel myViewModel;
    private ListView mList;
    private List<City> mCities;
    private MyAdapter myAdapter;

    private TextView mTitle;
    private Toolbar mToolbar;

    private int provinceId;
    private String provinceName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowUtil.setStatusBarTransparent(this);
        setContentView(R.layout.activity_choose_city);

        initData();
        initView();


    }

    private void initData() {
        provinceId = getIntent().getIntExtra("province_id", 0);
        provinceName = getIntent().getStringExtra("province_name");

        myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        myViewModel.requestCityData(provinceId);
        myViewModel.getCityData().observe(this, new Observer<List<City>>() {
            @Override
            public void onChanged(@Nullable List<City> cities) {
                mCities = cities;
                myAdapter = new MyAdapter(mCities);
                mList.setAdapter(myAdapter);
            }
        });

    }

    private void initView() {
        mList = (ListView) findViewById(R.id.list);
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ChooseCityActivity.this, ChooseCountyActivity.class);
                intent.putExtra("province_id", provinceId);
                intent.putExtra("city_id", mCities.get(position).getId());
                intent.putExtra("city_name", mCities.get(position).getName());
                startActivity(intent);
            }
        });

        mTitle = (TextView) findViewById(R.id.title);
        mTitle.setText(provinceName);

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
