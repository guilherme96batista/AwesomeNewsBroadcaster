package pt.guilhermerodrigues.awesomenewsbrodcaster.data

import pt.guilhermerodrigues.awesomenewsbrodcaster.data.models.ResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticlesApi {
    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String
    ): ResponseDto
}