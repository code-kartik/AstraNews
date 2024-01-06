package com.example.diceroll

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // leave the above code as it is
        val rollButton: Button = findViewById(R.id.button2) // setting a button

        // what to do when button is pressed
        rollButton.setOnClickListener{
            val toast = Toast.makeText(this, "Dice Rolled!", Toast.LENGTH_LONG)
            toast.show() // show text when button is pressed

            val dice = Dice(6) // dice object
            val diceRoll = dice.roll() // rolling the dice
            val diceImage: ImageView = findViewById(R.id.imageView)
            // change image when number changes
            when(diceRoll){
                1 -> diceImage.setImageResource(R.drawable.dice_1)
                2 -> diceImage.setImageResource(R.drawable.dice_2)
                3 -> diceImage.setImageResource(R.drawable.dice_3)
                4 -> diceImage.setImageResource(R.drawable.dice_4)
                5 -> diceImage.setImageResource(R.drawable.dice_5)
                6 -> diceImage.setImageResource(R.drawable.dice_6)
            }
        }
    }
}

// dice class
class Dice(val sides: Int){
    fun roll(): Int{
        return (1..sides).random()
    }
}