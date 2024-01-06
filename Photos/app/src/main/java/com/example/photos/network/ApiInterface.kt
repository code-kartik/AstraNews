package com.example.photos.network


import com.example.photos.dataclasses.UrlClass
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url
import java.sql.Timestamp

//Interface defining API endpoints for interacting with the Unsplash service.
interface ApiInterface {

    /*
     * Get a list of photos from Unsplash.
     * clientId Unsplash API client ID.
     * perPage Number of photos per page.*/
    @GET("/photos") //endpoint= "/photos"
    fun getPictures(@Query("client_id") clientId:String, @Query("per_page") perPage: Int): Call<List<UrlClass>?>?

    /*
    * Get a picture from select topic*/
    @GET
    fun getPicturesByTopic(@Url url:String, @Query("client_id") clientId:String): Call<List<UrlClass>?>?

    //search for the photo on unsplash based on specific query
    @GET("/search/photos")
    fun getPicturesBySearch(@Query("query") query:String, @Query("client_id") clientId:String, @Query("per_page") perPage:Int): Call<List<UrlClass>?>?
}