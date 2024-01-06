package com.example.photos.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.photos.R
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class FilterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)

        var isSearchUsed = false //boolean value to determine whether search is used or chip is selected

        /*
        * Listener for Chip Group to capture when chip is checked
        * returns the text of the chip selected and calls the function setResultAndFinish() to send result to MainActivity*/
        val chipGroup = findViewById<ChipGroup>(R.id.chipGroup)
        chipGroup.setOnCheckedChangeListener { group, checkedId ->
            val selectedChip = findViewById<Chip>(checkedId)?.text.toString()
            setResultAndFinish(false, null, selectedChip=selectedChip)
        }

        // if the searchButton is pressed, the isSearchUsed value will be set to true and
        // setResultAndFinish() will be called to send result to MainActivity
        val searchButton = findViewById<Button>(R.id.Search)
        val editText = findViewById<TextInputLayout>(R.id.editText)
        searchButton.setOnClickListener {
            val searchText = editText.editText?.text.toString()
            setResultAndFinish(true, searchText, null)
        }
    }

    /*
    * sets the result of current activity and finishes the activity
    * This method creates an intent and attaches data to it
    * then it sets the result of the activity and finish it
    * isSearchUsed: is used to determine if search button is used or not
    * searchText: is used to get the search text from the text field
    * selectedChip: is used to get the value of chip selected*/
    private fun setResultAndFinish(isSearchUsed: Boolean, searchText: String?, selectedChip: String?) {
        val resultIntent = Intent().apply {
            putExtra("isSearchUsed",isSearchUsed)
            putExtra("chipSelected",selectedChip)
            putExtra("searchText",searchText)
        }
        //sets the result of activity as RESULT_OK
        setResult(RESULT_OK, resultIntent)
        //finish the activity
        finish()
    }
}