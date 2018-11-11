package com.example.halong.myapplication.activity.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import com.example.halong.myapplication.R;
import com.example.halong.myapplication.activity.choose.ChooseProvinceActivity;
import com.example.halong.myapplication.service.MyService;
import com.example.halong.myapplication.utils.WindowUtil;

/**
 * 总结：
 * #getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);配合android:fitsSystemWindows="true"实现沉浸式模式。
 * #Toolbar Drawer ActionBarDrawerToggle实现动画的三道杠，*onBackPressed()
 *
 * @ Activity两大职责：视图展示->(展示数据)  事件响应->(视图动画 获取数据 改变数据)
 * @ Service: 获取数据 改变数据 监视信息 发出信息
 */
public class MainActivity extends AppCompatActivity {
    private MyService myService;
    private ServiceConnection conn;

    private MainViewModel mainViewModel;
    private MainData mainData = new MainData();
    private MainViews mainViews = new MainViews();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置状态栏全透明，会默认布局与状态栏重叠
        // 配合Toolbar->android:fitsSystemWindows="true"设置布局与状态栏衔接，不重叠
        WindowUtil.setStatusBarTransparent(this);

        setContentView(R.layout.activity_main);

        initData();
        initView();
        bindView();
        startMyService();
    }


    private void initData() {
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        SharedPreferences spf = PreferenceManager.getDefaultSharedPreferences(this);
        mainData.weatherString = spf.getString("weatherString", null);
        mainData.weatherId = spf.getString("weatherId", null);
    }


    private void initView() {
        mainViews.mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mainViews.mDrawer = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mainViews.mDrawer, mainViews.mToolbar, 0, 0);
        mainViews.mDrawer.addDrawerListener(toggle);//设置toggle动画
        toggle.syncState(); //设置toggle点击事件响应
        mainViews.mText = (TextView) findViewById(R.id.text);
        mainViews.mTitle = findViewById(R.id.title);
    }

    private void bindView() {
        if (mainData.weatherId == null) {
            startActivity(new Intent(this, ChooseProvinceActivity.class));
        }

        if (mainData.weatherString == null) {
            mainViewModel.requestWeatherString(mainData.weatherId);
        } else {
            mainViews.mText.setText(mainData.weatherString);
        }

        mainViewModel.getWeatherStringData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                mainViews.mText.setText(s);
            }
        });

        mainViews.mTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ChooseProvinceActivity.class));
            }
        });
    }


    private void startMyService() {
        Intent intent = new Intent(this, MyService.class);
        startService(intent);

        conn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                myService = ((MyService.MyBinder) service).getService();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                conn = null;
            }
        };
        bindService(intent, conn, 0);
    }

    @Override
    protected void onDestroy() {
        if (conn != null) {
            unbindService(conn);
        }
        super.onDestroy();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        mainData.weatherId = intent.getStringExtra("weatherId");
        mainViewModel.requestWeatherString(mainData.weatherId);
    }

    @Override
    public void onBackPressed() {
        if (mainViews.mDrawer.isDrawerOpen(GravityCompat.START)) {
            mainViews.mDrawer.closeDrawer(GravityCompat.START);
            return;
        }
        super.onBackPressed();
    }
}
