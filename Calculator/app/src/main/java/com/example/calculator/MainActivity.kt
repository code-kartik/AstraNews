package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Vibrator
import android.widget.TextView
import androidx.cardview.widget.CardView
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject.NULL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val vibrator:Vibrator = getSystemService(VIBRATOR_SERVICE) as Vibrator


        val allClear:CardView = findViewById(R.id.ac)
        val zero:CardView = findViewById(R.id.zero)
        val one:CardView = findViewById(R.id.one)
        val two:CardView = findViewById(R.id.two)
        val three:CardView = findViewById(R.id.three)
        val four:CardView = findViewById(R.id.four)
        val five:CardView = findViewById(R.id.five)
        val six:CardView = findViewById(R.id.six)
        val seven:CardView = findViewById(R.id.seven)
        val eight:CardView = findViewById(R.id.eight)
        val nine:CardView = findViewById(R.id.nine)
        val decimal:CardView = findViewById(R.id.decimal)
        val plus:CardView = findViewById(R.id.plus)
        val minus:CardView = findViewById(R.id.minus)
        val divide:CardView = findViewById(R.id.divide)
        val backspace:CardView = findViewById(R.id.backspace)
        val multiply:CardView = findViewById(R.id.multiply)
        val openBracket:CardView = findViewById(R.id.open_parantheses)
        val closeBracket:CardView = findViewById(R.id.closed_parantheses)
        val equals:CardView = findViewById(R.id.equals)
        val inputText = findViewById<TextView>(R.id.inputtext)
        val result:TextView = findViewById(R.id.output)

        var leftSideInput = ''

        allClear.setOnClickListener {
            inputText.setText("")
            result.setText("")
            vibrator.vibrate(100)
        }

        zero.setOnClickListener{
            inputText.append("0")
            vibrator.vibrate(100)
        }

        one.setOnClickListener{
            inputText.append("1")
            vibrator.vibrate(100)
        }

        two.setOnClickListener{
            inputText.append("2")
            vibrator.vibrate(100)
        }

        three.setOnClickListener{
            inputText.append("3")
            vibrator.vibrate(100)
        }

        four.setOnClickListener{
            inputText.append("4")
            vibrator.vibrate(100)
        }

        five.setOnClickListener{
            inputText.append("5")
            vibrator.vibrate(100)
        }

        six.setOnClickListener{
            inputText.append("6")
            vibrator.vibrate(100)
        }

        seven.setOnClickListener{
            inputText.append("7")
            vibrator.vibrate(100)
        }

        eight.setOnClickListener{
            inputText.append("8")
            vibrator.vibrate(100)
        }

        nine.setOnClickListener{
            inputText.append("9")
            vibrator.vibrate(100)
        }

        decimal.setOnClickListener{
            inputText.append(".")
            vibrator.vibrate(100)
        }

        plus.setOnClickListener{
            oldNumber = inputText.editableText.text
            inputText.append("+")
            vibrator.vibrate(100)
        }

        minus.setOnClickListener{
            inputText.append("-")
            vibrator.vibrate(100)
        }

        multiply.setOnClickListener{
            inputText.append("*")
            vibrator.vibrate(100)
        }

        divide.setOnClickListener{
            inputText.append("/")
            vibrator.vibrate(100)
        }

        backspace.setOnClickListener{
            val length = inputText.length()
            if(length>0){
                inputText.text = inputText.text.subSequence(0,length-1)
            }
            vibrator.vibrate(100)
        }

        openBracket.setOnClickListener{
            inputText.append("(")
            vibrator.vibrate(100)
        }

        closeBracket.setOnClickListener{
            inputText.append(")")
            vibrator.vibrate(100)
        }

        equals.setOnClickListener{
            vibrator.vibrate(100)
            result.setText(leftSideInput)

        }
    }
}