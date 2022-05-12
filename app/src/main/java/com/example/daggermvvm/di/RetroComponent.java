package com.example.daggermvvm.di;

import com.example.daggermvvm.viewmodel.MainViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {RetroModule.class})
public interface RetroComponent {

    public void inject(MainViewModel viewModel);
}
