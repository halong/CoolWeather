package com.example.halong.myapplication.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.halong.myapplication.service.MyService;
import com.example.halong.myapplication.R;
import com.example.halong.myapplication.utils.WindowUtil;

/**
 * 总结：
 * #getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);配合android:fitsSystemWindows="true"实现沉浸式模式。
 * #Toolbar Drawer ActionBarDrawerToggle实现动画的三道杠，*onBackPressed()
 */
public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private DrawerLayout mDrawer;
    private MyService myService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置状态栏全透明，会默认布局与状态栏重叠
        // 配合Toolbar->android:fitsSystemWindows="true"设置布局与状态栏衔接，不重叠
        WindowUtil.setStatusBarTransparent(this);

        setContentView(R.layout.activity_main);

        initData();
        initView();
        startMyService();

        startActivity(new Intent(this,ChooseProvinceActivity.class));
    }

    private void initData() {

    }

    private void startMyService() {
        Intent intent=new Intent(this,MyService.class);
        startService(intent);
        ServiceConnection conn=new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                myService=((MyService.MyBinder) service).getService();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        bindService(intent,conn, 0);
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawer, mToolbar, 0, 0);
        mDrawer.addDrawerListener(toggle);//设置toggle动画
        toggle.syncState(); //设置toggle点击事件响应
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
            return;
        }
        super.onBackPressed();
    }
}
