package com.example.halong.myapplication.utils;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadUtil {
    private static ExecutorService mExecutorService;
    public static ExecutorService getExecutorService() {
        if (mExecutorService == null) {
            mExecutorService = Executors.newCachedThreadPool();
        }
        return mExecutorService;
    }
}