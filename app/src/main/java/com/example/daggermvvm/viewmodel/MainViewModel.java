package com.example.daggermvvm.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.daggermvvm.app.MyApplication;
import com.example.daggermvvm.di.RetroServiceInterface;
import com.example.daggermvvm.model.RecyclerData;
import com.example.daggermvvm.model.RecyclerList;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends AndroidViewModel {

    @Inject
    RetroServiceInterface mService;

    private final MutableLiveData<RecyclerList> liveDataList;

    public MainViewModel(@NonNull Application application) {
        super(application);
        ((MyApplication)application).getRetroComponent().inject(MainViewModel.this);
        liveDataList = new MutableLiveData<>();
    }

    public MutableLiveData<RecyclerList> getLiveDataListObserver() {
        return liveDataList;
    }

    public void makeApiCall() {
        Call<RecyclerList> call = mService.getDataFromApi("newyork");
        call.enqueue(new Callback<RecyclerList>() {
            @Override
            public void onResponse(@NonNull Call<RecyclerList> call, @NonNull Response<RecyclerList> response) {
                if (response.isSuccessful()) {
                    liveDataList.postValue(response.body());
                } else {
                    liveDataList.postValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<RecyclerList> call, @NonNull Throwable t) {
                Log.d("@@@@@", t.getLocalizedMessage());
                liveDataList.postValue(null);
            }
        });
    }
}
