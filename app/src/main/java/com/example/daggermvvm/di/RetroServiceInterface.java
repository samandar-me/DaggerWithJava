package com.example.daggermvvm.di;

import com.example.daggermvvm.model.RecyclerList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetroServiceInterface {

    @GET("repositories")
    Call<RecyclerList> getDataFromApi(@Query("q") String query);
}
