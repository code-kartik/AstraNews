package com.example.previousyearquestions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val next: Button = findViewById(R.id.nextButton)
        loadImage()

        next.setOnClickListener { loadImage() }
    }

    private fun loadImage() {
        val image: ImageView = findViewById(R.id.imageView)


        val nsfw = listOf("waifu", "trap", "blowjob")
        val item = nsfw.random()

        val queue = Volley.newRequestQueue(this)
        val urlMain = "https://api.waifu.pics/nsfw/$item"

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, urlMain, null,
            { response ->
                val url = response.getString("url")
                Glide.with(this).load(url).into(image)
            },
            { error ->
                Glide.with(this).load("https://i.stack.imgur.com/Q0jaC.png").into(image)
            }
        )
        queue.add(jsonObjectRequest)
    }
}