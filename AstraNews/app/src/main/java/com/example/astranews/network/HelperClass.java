package com.example.astranews.network;

import com.squareup.moshi.Moshi;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class HelperClass {
    final String BASE_URL = "https://api.spaceflightnewsapi.net/";

    Moshi moshi = new Moshi.Builder().build();

    Retrofit getInstance(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build();
    }
}
