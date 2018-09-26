package com.example.halong.myapplication;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MyApplication extends Application {
    private static List<Activity> activityList = new ArrayList<>();


    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycle();
    }

    private void registerActivityLifecycle(){
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                activityList.add(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                activityList.remove(activity);
            }
        });
    }

    public static void exit() {
        if (activityList != null && activityList.size() > 0) {
            for (Activity activity :
                    activityList) {
                activity.finish();
            }
        }
    }

}
