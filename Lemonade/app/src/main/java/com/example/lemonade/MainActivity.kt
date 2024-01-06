package com.example.lemonade

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pushButton: Button = findViewById(R.id.button)
        val imgLemon: ImageView = findViewById(R.id.imageView2)
        imgLemon.setImageResource(R.drawable.lemon_tree)
        pushButton.setOnClickListener{
            pushButton.text = "Click to make lemonade".toString()
            imgLemon.setImageResource(R.drawable.lemon)

            pushButton.setOnClickListener{
                pushButton.text = "Click to drink the lemonade"
                imgLemon.setImageResource(R.drawable.lemonade_drink)

                pushButton.setOnClickListener{
                    pushButton.text = "You have emptied the glass! Click to exit the Application"
                    imgLemon.setImageResource(R.drawable.empty_glass)

                    pushButton.setOnClickListener{
                        exitProcess(-1)
                    }
                }
            }
        }
    }
}