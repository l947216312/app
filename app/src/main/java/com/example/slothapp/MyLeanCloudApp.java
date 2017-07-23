package com.example.slothapp;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;

public class MyLeanCloudApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(this,"PdUnsskWKzBIobgQJAoOVOG2-gzGzoHsz","3uE2TGjlA6fVaIyP77clnfP5");
        AVOSCloud.setDebugLogEnabled(true);
    }
}