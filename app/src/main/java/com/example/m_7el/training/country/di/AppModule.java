package com.example.m_7el.training.country.di;

import com.example.m_7el.training.country.utils.PhotoManager;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    @Provides
    PhotoManager getPhotoMangerModule() {
        return new PhotoManager();
    }
}
