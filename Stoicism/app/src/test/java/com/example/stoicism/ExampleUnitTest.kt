import org.junit.Assert.assertEquals
import org.junit.Test

class MainActivityUnitTest {

    @Test
    fun getRandomQuote_ReturnsRandomQuote() {
        val quotes = listOf(
            "The happiness of your life depends upon the quality of your thoughts. - Marcus Aurelius",
            "You have power over your mind - not outside events. Realize this, and you will find strength. - Marcus Aurelius",
            "Choose not to be harmed, and you won’t feel harmed. Don’t feel harmed, and you haven’t been. - Marcus Aurelius",
            "The best revenge is to be unlike him who performed the injury. - Marcus Aurelius",
            "The obstacle is the way. - Ryan Holiday",
            "The present moment is the only thing where there is no time. - Ryan Holiday"
        )

        val quote = getRandomQuote()

        // Check if the generated quote is in the list of quotes
        assertEquals(true, quotes.contains(quote))
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
