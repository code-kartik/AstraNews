package com.example.astranews.network;

import com.example.astranews.dataclasses.JsonClass;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("/v4/articles")
    Call<JsonClass> getArticle();
}
