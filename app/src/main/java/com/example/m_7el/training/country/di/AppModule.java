package com.example.m_7el.training.country.di;

import com.example.m_7el.training.country.ApiImp;
import com.example.m_7el.training.country.Apis;
import com.example.m_7el.training.country.utils.PhotoManager;
import com.example.m_7el.training.country.utils.PhotoManagerImp;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    @Provides
    PhotoManager getPhotoMangerModule() {
        return new PhotoManagerImp();
    }

    @Provides
    Apis getApi() {
        return new ApiImp();
    }
}
