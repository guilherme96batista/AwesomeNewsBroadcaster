package pt.guilhermerodrigues.awesomenewsbroadcaster

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Test

class BuildConfigFlavorTest {

    @Test
    fun `NEWS_SOURCE matches flavor`() {
        when (BuildConfig.FLAVOR) {
            "bbc" -> assertEquals("bbc-news", BuildConfig.news_source)
            "cnn" -> assertEquals("cnn", BuildConfig.news_source)
            else -> assertTrue(BuildConfig.news_source.isNotBlank()) // default / other
        }
    }
}