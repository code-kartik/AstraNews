package com.example.photos.objects

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

//HelperObject is a Singelton object for making network requests using Retrofit Library
object HelperObject {
    const val client_id = "ZwkVmvPKRmmvbgHmnUZPMZkLgU_ikcelfJ9bGR9mMtc" //client_id or API_KEY to be used in the BASE_URL
    const val BASE_URL = "https://api.unsplash.com"; //base url for the API call

    //moshi is used to with retrofit for making requests
    val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    //method to get singleton instance of retrofit
    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(OkHttpClient.Builder().cache(null).build())
            .build()
    }
}