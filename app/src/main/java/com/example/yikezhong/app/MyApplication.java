package com.example.yikezhong.app;

import android.app.Application;
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


    }
}
