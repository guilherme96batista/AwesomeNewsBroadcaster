package pt.guilhermerodrigues.awesomenewsbroadcaster.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pt.guilhermerodrigues.awesomenewsbroadcaster.data.models.StatusDto
import pt.guilhermerodrigues.awesomenewsbroadcaster.domain.Article
import pt.guilhermerodrigues.awesomenewsbroadcaster.domain.toDomainOrNull

class ArticlesRepo(
    private val api: ArticlesApi,
    private val apiKey: String,
    private val source: String
) {
    suspend fun getTopArticles(): List<Article> = withContext(Dispatchers.IO) {
        val response = api.getTopHeadlines(source, apiKey)
        if (response.status == StatusDto.ok)
            response.articles.mapNotNull{it.toDomainOrNull()}.sortedByDescending { it.publishedAt }
        else
            throw Exception(response.message ?: "Erro desconhecido")
    }
}