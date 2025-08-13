package pt.guilhermerodrigues.awesomenewsbrodcaster.domain

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertNull
import org.junit.Test
import pt.guilhermerodrigues.awesomenewsbrodcaster.data.models.ArticleDto
import pt.guilhermerodrigues.awesomenewsbrodcaster.data.models.SourceDto
import java.time.Instant

class ArticleDtoMapperTest {

    @Test
    fun `valid dto maps to domain`() {
        val dto = ArticleDto(
            source = SourceDto(id = "bbc-news", name = "BBC"),
            author = "Gui",
            title = "HelloWorld",
            description = "description",
            url = "https://example.com/x",
            urlToImage = "https://example.com/img.jpg",
            publishedAt = "2025-08-12T20:17:00Z",
            content = "content"
        )

        val domainArticle = dto.toDomainOrNull()
        assertNotNull(domainArticle)
        domainArticle!!
        assertEquals("bbc-news", domainArticle.id)
        assertEquals("HelloWorld", domainArticle.title)
        assertEquals("description", domainArticle.description)
        assertEquals("content", domainArticle.content)
        assertEquals("https://example.com/img.jpg", domainArticle.imageUrl)
        assertEquals(Instant.parse("2025-08-12T20:17:00Z"), domainArticle.publishedAt)
    }

    @Test fun `missing source id returns null`() {
        val dto = ArticleDto(
            source = SourceDto(id = null, name = "BBC"),
            title = "HelloWorld",
            url = "https://example.com/x",
            publishedAt = "2025-08-12T20:17:00Z"
        )
        assertNull(dto.toDomainOrNull())
    }

    @Test fun `missing title returns null`() {
        val dto = ArticleDto(
            source = SourceDto(id = "bbc-news", name = "BBC"),
            title = null,
            url = "https://example.com/x",
            publishedAt = "2025-08-12T20:17:00Z"
        )
        assertNull(dto.toDomainOrNull())
    }

    @Test fun `bad date returns null`() {
        val dto = ArticleDto(
            source = SourceDto(id = "bbc-news", name = "BBC"),
            title = "HelloWorld",
            url = "https://example.com/x",
            publishedAt = "not-a-date"
        )
        assertNull(dto.toDomainOrNull())
    }
}