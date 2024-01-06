package com.example.photos.activities

//Imports
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.widget.ImageButton
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.photos.dataclasses.UrlClass
import com.example.photos.R
import com.example.photos.adapters.RecyclerAdapter
import com.example.photos.network.ApiInterface
import com.example.photos.objects.HelperObject
import com.example.photos.objects.HelperObject.client_id
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    private lateinit var mAdapter: RecyclerAdapter //mAdapter is a custom recycler adapter used to add views to the recycler view
    private val FILTER_CHIP_CODE = 1

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // handling all the button clicks
        val backButton = findViewById<ImageButton>(R.id.goBack);
        backButton.setOnClickListener {
            vibrate()
            exitProcess(0);
        }

        val reloadButton = findViewById<ImageButton>(R.id.reload)
        reloadButton.setOnClickListener {
            vibrate()
            Log.d("ButtonClicked","Reload button clicked")
            recreate()
            fetchData()
        }
        val filterButton = findViewById<ImageButton>(R.id.filter)
        filterButton.setOnClickListener {
            vibrate()
            val intent = Intent(this, FilterActivity::class.java)
            startActivityForResult(intent, FILTER_CHIP_CODE)
        }

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this) //deciding the layout for recycler view => set to linear layout
        mAdapter = RecyclerAdapter() // creating an adapter object
        recyclerView.adapter = mAdapter //setting the recycler object to custom adapter we created

        fetchData() // fetching the data from API to recycler view
    }

    /*
    * This method is used to handle result from FilterActivity.kt
    * which provides data values like whether the search button was clicked or a chip was selected
    * requestCode: The code initially supplied to StartActivityForResult(), to identify the activity
    * resultCode: integer code returned by child activity
    * data: Intent that carries result data*/
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == FILTER_CHIP_CODE && resultCode == RESULT_OK){
            val isSearchUsed = data?.getBooleanExtra("isSearchUsed", false)
            val selectedChip = data?.getStringExtra("selectedChip")
            val searchText = data?.getStringExtra("searchText")

            if(isSearchUsed == true){
                fetchDataBySearch(searchText)
            } else {
                fetchDataByTopic(selectedChip)
            }
        }
    }

    /*The Retrofit library is used to create an instance of the API interface (ApiInterface),
     * and the getPictures function is called to perform a GET request for a list of UrlClass objects.
     * The response is checked for success, and if successful, the UI is updated on the main thread
     * using withContext(Dispatchers.Main). The updateAdapter function is then called to refresh
     * the UI with the retrieved data.*/
    private fun fetchData() {
        // using coroutines to perform asynchronous operations like network requests
        //GlobalScope is predefined coroutine suitable for global level operations
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val apiCall = HelperObject.getInstance().create(ApiInterface::class.java) // creating an instance of retrofit API Interface
                val call: Call<List<UrlClass>?>? = apiCall.getPictures(client_id, 50) //calling the function from API Interface to perform GET request

                //executing the call and getting the response
                val response = call?.execute()
                if (response?.isSuccessful == true) {
                    val responseBody = response.body()
                    withContext(Dispatchers.Main){//updating UI in main thread
                        if (responseBody != null) {
                            updateAdapter(responseBody) //using the adapter to update the UI
                        }
                    }
                }
            } catch (e: Exception) {
                //Logging the errors
                Log.e("NetworkError", e.message ?: "Unknown error")
            }
        }
    }

    /*
    * This function is very similar to the one above
    * This function also uses Retrofit API interface object
    * to create a GET Request
    * Except this is called when the user wants to search for a photo unlike the above function
    * which is called automatically when the app starts*/
    private fun fetchDataBySearch(searchText: String?) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val apiCall = HelperObject.getInstance().create(ApiInterface::class.java)
                val call: Call<List<UrlClass>?>? =
                    searchText?.let { apiCall.getPicturesBySearch(it, client_id, 20) }

                val response = call?.execute()
                if (response?.isSuccessful == true) {
                    val responseBody = response.body()
                    withContext(Dispatchers.Main){
                        if (responseBody != null) {
                            updateAdapter(responseBody)
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e("NetworkError", e.message ?: "Unknown error")
            }
        }
    }

    /*
    * This function is also very similar to the fetchData() function
    * This function also uses Retrofit API interface object
    * to create a GET request to get a photo
    * Except this function is used when user wants to filter the photos
    * based on certain topics*/
    private fun fetchDataByTopic(chipSelected: String?) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val apiCall = HelperObject.getInstance().create(ApiInterface::class.java)

                //based on the single chip selection, the endpoint is being set for every choice, based on the choice, the API is called
                val call: Call<List<UrlClass>?>? = when (chipSelected) {
                    "Wallpapers" -> apiCall.getPicturesByTopic(
                        "topics/wallpapers/photos",
                        client_id
                    )
                    "3D Renders" -> apiCall.getPicturesByTopic(
                        "topics/3d-renders/photos",
                        client_id
                    )
                    "Nature" -> apiCall.getPicturesByTopic(
                        "topics/nature/photos",
                        client_id
                    )
                    "Textures & Patterns" -> apiCall.getPicturesByTopic(
                        "topics/textures-patterns/photos",
                        client_id
                    )
                    "Film" -> apiCall.getPicturesByTopic(
                        "topics/film/photos",
                        client_id
                    )
                    "Animals" -> apiCall.getPicturesByTopic(
                        "topics/animals/photos",
                        client_id
                    )
                    "Street Photography" -> apiCall.getPicturesByTopic(
                        "topics/street-photography/photos",
                        client_id
                    )
                    "Travel" -> apiCall.getPicturesByTopic(
                        "topics/travel/photos",
                        client_id
                    )

                    else -> {
                        apiCall.getPictures(client_id, perPage = 50)
                    }
                }
                val response = call?.execute()
                if (response?.isSuccessful == true) {
                    val responseBody = response.body()
                    withContext(Dispatchers.Main){
                        if (responseBody != null) {
                            updateAdapter(responseBody)
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e("NetworkError", e.message ?: "Unknown error")
            }
        }
    }

    /*
    * Updates the RecyclerView Adapter with new set of items
    * responsible for data displayed in RecyclerView
    * urlClassArray notifies that UrlClass items to be displayed in the RecyclerView*/
    private fun updateAdapter(urlClassArray: List<UrlClass>) {
        mAdapter.items.clear()
        mAdapter.items.addAll(urlClassArray)
        mAdapter.notifyDataSetChanged()
    }

    //This is simple function to provide feedback to the user when the button is being pressed
    @RequiresApi(Build.VERSION_CODES.O)
    private fun vibrate() {
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?

        // Check if the device has a vibrator
        if (vibrator?.hasVibrator() == true) {
            // Vibrate for 100 milliseconds
            vibrator.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE))
        }
    }

}