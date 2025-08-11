package pt.guilhermerodrigues.awesomenewsbrodcaster.data

import kotlinx.serialization.Serializable


@Serializable
data class Response (
    val status: String,
    val totalResults: Int,
    val message: String?,
    val articles: List<Article>?
)