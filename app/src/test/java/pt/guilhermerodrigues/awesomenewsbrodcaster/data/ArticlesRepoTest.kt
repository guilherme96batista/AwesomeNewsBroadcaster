package pt.guilhermerodrigues.awesomenewsbrodcaster.data

import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import pt.guilhermerodrigues.awesomenewsbrodcaster.data.models.ArticleDto
import pt.guilhermerodrigues.awesomenewsbrodcaster.data.models.ResponseDto
import pt.guilhermerodrigues.awesomenewsbrodcaster.data.models.SourceDto
import pt.guilhermerodrigues.awesomenewsbrodcaster.data.models.StatusDto
import java.time.Instant

class ArticlesRepoTest {

    private val api = mockk<ArticlesApi>()

    @Test
    fun `success - maps, filters nulls, sorts desc by date`() = runBlocking {
        val source = "bbc-news"

        val a1 = ArticleDto(
            source = SourceDto(id = "bbc-news", name = "BBC"),
            title = "Newer",
            url = "https://ex.com/1",
            urlToImage = null,
            publishedAt = "2025-08-12T20:17:00Z",
            description = "d1",
            content = "c1"
        )
        val a2 = ArticleDto(
            source = SourceDto(id = "bbc-news", name = "BBC"),
            title = "Older",
            url = "https://ex.com/2",
            urlToImage = null,
            publishedAt = "2025-08-11T20:17:00Z",
            description = "d2",
            content = "c2"
        )
        val bad = ArticleDto( // dropped (no source id)
            source = SourceDto(id = null, name = "BBC"),
            title = "Bad",
            url = "https://ex.com/3",
            publishedAt = "2025-08-10T20:17:00Z"
        )

        val dto = ResponseDto(
            status = StatusDto.ok,
            totalResults = 3,
            message = null,
            articles = listOf(a2, bad, a1) // intentionally unsorted
        )

        // Whether your second param is @Header or @Query, we just match it with any()
        coEvery { api.getTopHeadlines(source, any()) } returns dto

        val repo = ArticlesRepo(api = api, apiKey = "KEY", source = source)
        val result = repo.getTopArticles()

        assertEquals(2, result.size)
        assertEquals("Newer", result[0].title)
        assertEquals(Instant.parse("2025-08-12T20:17:00Z"), result[0].publishedAt)
        assertEquals("Older", result[1].title)
    }

    @Test(expected = Exception::class)
    fun `error - throws with message`() {
        runBlocking {
            val source = "bbc-news"
            val dto = ResponseDto(
                status = StatusDto.error,
                totalResults = 0,
                message = "Nope",
                articles = emptyList()
            )

            coEvery { api.getTopHeadlines(source, any()) } returns dto

            val repo = ArticlesRepo(api = api, apiKey = "KEY", source = source)
            repo.getTopArticles()
        }
    }
}