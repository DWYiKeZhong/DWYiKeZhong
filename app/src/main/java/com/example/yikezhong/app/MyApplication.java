package com.example.yikezhong.app;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Fresco    的初始化全局配置类
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //Fresco
        Fresco.initialize(this);
        AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_NO);
    }
}
