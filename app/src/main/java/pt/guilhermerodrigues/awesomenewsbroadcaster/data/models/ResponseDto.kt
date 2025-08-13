package pt.guilhermerodrigues.awesomenewsbroadcaster.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ResponseDto (
    val status: StatusDto,
    val totalResults: Int? = null,
    val message: String? = null,
    val articles: List<ArticleDto> = emptyList()
)

enum class StatusDto {
    @SerialName("ok") ok,
    @SerialName("error") error
}