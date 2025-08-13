package pt.guilhermerodrigues.awesomenewsbrodcaster.domain

import pt.guilhermerodrigues.awesomenewsbrodcaster.data.models.ArticleDto
import java.time.Instant


data class Article(
    val id: String,
    val title: String,
    val description: String?,
    val content: String?,
    val imageUrl: String?,
    val publishedAt: Instant
)

fun ArticleDto.toDomainOrNull(): Article? {
    val id = this.source?.id ?: return null
    val title = this.title ?: return null

    val publishedAt = runCatching {
        Instant.parse(this.publishedAt) // required for sorting
    }.getOrNull() ?: return null

    return Article(
        id = id,
        title = title,
        description = description,
        content = content,
        imageUrl = urlToImage,
        publishedAt = publishedAt
    )
}