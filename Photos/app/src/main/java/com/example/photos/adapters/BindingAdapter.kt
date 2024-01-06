package com.example.photos.adapters

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import coil.load

//Binding Adapter is for loading and displaying images using Coil Library
@BindingAdapter("imgUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    //if imgUrl is not null
    imgUrl?.let {
        //convert imgUri to Uri and set scheme to "https"
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        //use coil library to load and display image
        imgView.load(imgUri)
    }
}