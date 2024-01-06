import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.stoicism.R


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set the background color to black
        window.decorView.setBackgroundColor(Color.BLACK)

        // Set the button background color to yellow
        val button: Button = findViewById(R.id.button)
        button.setBackgroundColor(Color.YELLOW)

        val randomQuote:TextView = findViewById(R.id.quoteTextView)

        button.setOnClickListener {
            val quote  = getRandomQuote()
            randomQuote.text = quote
        }
    }

    private fun getRandomQuote(): String {
        val quotes = listOf(
            "The happiness of your life depends upon the quality of your thoughts. - Marcus Aurelius",
            "You have power over your mind - not outside events. Realize this, and you will find strength. - Marcus Aurelius",
            "Choose not to be harmed, and you won’t feel harmed. Don’t feel harmed, and you haven’t been. - Marcus Aurelius",
            "The best revenge is to be unlike him who performed the injury. - Marcus Aurelius",
            "The obstacle is the way. - Ryan Holiday",
            "The present moment is the only thing where there is no time. - Ryan Holiday"
        )
        return quotes.random()
    }
}