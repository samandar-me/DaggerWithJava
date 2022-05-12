package com.example.daggermvvm.app;

import android.app.Application;

import com.example.daggermvvm.di.DaggerRetroComponent;
import com.example.daggermvvm.di.RetroComponent;
import com.example.daggermvvm.di.RetroModule;


public class MyApplication extends Application {

    private RetroComponent retroComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        retroComponent = DaggerRetroComponent.builder()
                .retroModule(new RetroModule())
                .build();
    }

    public RetroComponent getRetroComponent() {
        return retroComponent;
    }
}
