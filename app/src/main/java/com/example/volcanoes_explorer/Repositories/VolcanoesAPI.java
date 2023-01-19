package com.example.volcanoes_explorer.Repositories;

import com.example.volcanoes_explorer.Models.Volcano;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface VolcanoesAPI {
    @GET("volcanoes")
    Single<List<Volcano>> getVolcanoes();
}
