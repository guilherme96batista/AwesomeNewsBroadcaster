package pt.guilhermerodrigues.awesomenewsbrodcaster.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pt.guilhermerodrigues.awesomenewsbrodcaster.data.models.StatusDto
import pt.guilhermerodrigues.awesomenewsbrodcaster.domain.Article
import pt.guilhermerodrigues.awesomenewsbrodcaster.domain.toDomainOrNull

class ArticlesRepo(
    private val api: ArticlesApi,
    private val apiKey: String
) {
    suspend fun getTopArticles(source: String): List<Article> = withContext(Dispatchers.IO) {
        val response = api.getTopHeadlines(source, apiKey)
        if (response.status == StatusDto.ok)
            response.articles.mapNotNull{it.toDomainOrNull()}.sortedByDescending { it.publishedAt }
        else
            throw Exception(response.message ?: "Erro desconhecido")
    }
}