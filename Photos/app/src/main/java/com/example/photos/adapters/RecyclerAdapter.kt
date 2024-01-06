package com.example.photos.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.photos.dataclasses.UrlClass
import com.example.photos.R
import com.squareup.picasso.Picasso

//recycler adapter for displaying list of images
class RecyclerAdapter : RecyclerView.Adapter<ViewHolder>() {

    //list that stores the items
    var items: ArrayList<UrlClass> = ArrayList()

    //is called when a new ViewHolder of given type is required to display an item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //inflate the view for an individual item
        val view = LayoutInflater.from(parent.context).inflate(R.layout.image_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        //returns the total number of items held by adapter
        return items.size
    }

    //is called to display the data at specified position
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //get data for current position
        val current = items[position]

        //bind the data to the view
        holder.title.text = current.alt_description
        holder.author.text = current.user?.name
        Picasso.get().load(current.urls?.thumb).into(holder.image)
    }

}

//View holder to hold the view of an individual item in the recycler view
class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    val image: ImageView = itemView.findViewById(R.id.imageView1)
    val title: TextView = itemView.findViewById(R.id.title1)
    val author: TextView = itemView.findViewById(R.id.author1)
}
