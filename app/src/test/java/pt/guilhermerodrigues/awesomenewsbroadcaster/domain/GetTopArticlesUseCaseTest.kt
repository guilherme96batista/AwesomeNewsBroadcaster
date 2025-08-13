package pt.guilhermerodrigues.awesomenewsbroadcaster.domain

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Test
import pt.guilhermerodrigues.awesomenewsbroadcaster.core.AsyncState
import pt.guilhermerodrigues.awesomenewsbroadcaster.data.ArticlesRepo
import java.time.Instant

class GetTopArticlesUseCaseTest {

    private val repo = mockk<ArticlesRepo>()

    @Test
    fun `emits Loading then Success`() = runBlocking {
        val articles = listOf(
            Article(
                id = "id1",
                title = "HelloWorld",
                description = "description",
                content = "content",
                imageUrl = null,
                publishedAt = Instant.parse("2025-08-12T20:17:00Z")
            )
        )
        coEvery { repo.getTopArticles() } returns articles

        val usecase = GetTopArticlesUseCase(repo)
        val emissions = usecase(Unit).toList()
        //UseCase always emits a Loading before any data comes

        assertTrue(emissions[0] is AsyncState.Loading)
        val success = emissions[1] as AsyncState.Success<List<Article>>
        assertEquals("HelloWorld", success.data.first().title)

        coVerify(exactly = 1) { repo.getTopArticles() }
    }

    @Test
    fun `emits Loading then Error`() = runBlocking {
        coEvery { repo.getTopArticles() } throws RuntimeException("unexpected error")

        val usecase = GetTopArticlesUseCase(repo)
        val emissions = usecase(Unit).toList()

        assertTrue(emissions[0] is AsyncState.Loading)
        val err = emissions[1] as AsyncState.Error
        assertEquals("unexpected error", err.throwable.message)
    }
}