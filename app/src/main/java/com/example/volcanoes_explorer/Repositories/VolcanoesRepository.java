package com.example.volcanoes_explorer.Repositories;

import com.example.volcanoes_explorer.Models.Volcano;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class VolcanoesRepository {

    private static VolcanoesRepository instance;
    private VolcanoesAPI volcanoesAPI;

    public static VolcanoesRepository getInstance() {
        if (instance == null) {
            instance = new VolcanoesRepository();
        }
        return instance;
    }

    public VolcanoesRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://indonesia-public-static-api.vercel.app/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        volcanoesAPI = retrofit.create(VolcanoesAPI.class);
    }

    public Single<List<Volcano>> getVolcanoes() {
        return volcanoesAPI.getVolcanoes();
    }
}

