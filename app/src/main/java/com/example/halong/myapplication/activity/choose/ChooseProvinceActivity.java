package com.example.halong.myapplication.activity.choose;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.halong.myapplication.R;
import com.example.halong.myapplication.adapter.MyAdapter;
import com.example.halong.myapplication.bean.Province;
import com.example.halong.myapplication.utils.WindowUtil;

import java.util.List;

public class ChooseProvinceActivity extends AppCompatActivity {
    private ChooseViewModel mViewModel;

    private ListView mList;
    private List<Province> mProvinces;
    private ListAdapter adapter;

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowUtil.setStatusBarTransparent(this);
        setContentView(R.layout.activity_choose);

        initView();
        initData();
    }

    private void initData() {
        mViewModel = ViewModelProviders.of(this).get(ChooseViewModel.class);

        mViewModel.getProvinceData().observe(this, new Observer<List<Province>>() {
            @Override
            public void onChanged(@Nullable List<Province> provinces) {
                if (provinces == null || provinces.size() < 1)
                    mViewModel.requestProvinceData();
                mProvinces = provinces;
                adapter = new MyAdapter<Province>(provinces);
                mList.setAdapter(adapter);
            }
        });

    }

    private void initView() {
        mList = (ListView) findViewById(R.id.list);
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ChooseProvinceActivity.this, ChooseCityActivity.class);
                intent.putExtra("province_id", mProvinces.get(position).getId());
                intent.putExtra("province_name", mProvinces.get(position).getName());
                startActivity(intent);

            }
        });

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
