package com.example.halong.myapplication;

import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.halong.myapplication.adapter.MyAdapter;
import com.example.halong.myapplication.bean.Province;
import com.example.halong.myapplication.viewmodel.ChooseViewModel;

import java.util.List;

/**
 * 总结：getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);配合android:fitsSystemWindows="true"
 */
public class ChooseActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView mBack;
    private ListView mListProvince;
    private ListView mListCity;
    private ListView mListCounty;
    private HorizontalScrollView mScrollView;
    private ChooseViewModel mChooseViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏全透明
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        setContentView(R.layout.activity_choose);
        initData();
        initView();
    }

    private void initData() {
        mChooseViewModel= ViewModelProviders.of(this).get(ChooseViewModel.class);
        mChooseViewModel.requestProvinceData();

        mChooseViewModel.getProvinceLiveData().observe(this, new Observer<List<Province>>() {
            @Override
            public void onChanged(@Nullable List<Province> provinces) {
                mListProvince.setAdapter(new MyAdapter<Province>(provinces));
            }
        });

    }

    private void initView() {
        mBack = (ImageView) findViewById(R.id.back);
        mBack.setOnClickListener(this);
        mListProvince = (ListView) findViewById(R.id.list_Province);
        mListCity = (ListView) findViewById(R.id.list_city);
        mListCounty = (ListView) findViewById(R.id.list_county);
        mScrollView = (HorizontalScrollView) findViewById(R.id.scrollView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.back:
                finish();
                break;
        }
    }
}
