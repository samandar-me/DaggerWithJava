package com.example.daggermvvm.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetroModule {

    private String baseUrl = "https://api.github.com/search/"; //repositories?q=newyork

    @Provides
    @Singleton
    public RetroServiceInterface getRetroServiceInterface(Retrofit retrofit) {
        return retrofit.create(RetroServiceInterface.class);
    }

    @Singleton
    @Provides
    public Retrofit getRetroInstance() {

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
