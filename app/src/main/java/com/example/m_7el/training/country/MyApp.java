package com.example.m_7el.training.country;

import android.app.Application;

import com.example.m_7el.training.country.di.AppModule;
import com.example.m_7el.training.country.di.DaggerMyComponent;
import com.example.m_7el.training.country.di.MyComponent;


public class MyApp extends Application {
    private MyComponent myComponent;
    private static MyApp app;

    public static MyApp getApp() {
        return app;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        myComponent = DaggerMyComponent.builder()
                .appModule(new AppModule())
                .build();
    }

    public MyComponent getMyComponent() {
        return myComponent;
    }

}
