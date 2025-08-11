package pt.guilhermerodrigues.awesomenewsbrodcaster.data

import retrofit2.http.GET

interface TopArticlesApi {
    @GET("response")
    suspend fun response(): Response
}