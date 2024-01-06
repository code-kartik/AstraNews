package com.example.photos.dataclasses

import com.squareup.moshi.Json

/*
* Data class representing the structure of the response containing image URLs from the Unsplash API.*/
data class UrlClass(
    @Json(name = "alt_description") val alt_description: String?,
    @Json(name = "full") val full: String?,
    @Json(name = "urls") val urls:urls?,
    @Json(name = "user") val user:user?
)

//Data class representing different image URLs, particularly the regular-sized image URL.
data class urls(
    @Json(name="regular") val thumb:String?
)

//Data class representing information about the user of the image.
data class user(
    @Json(name="name") val name:String?
)