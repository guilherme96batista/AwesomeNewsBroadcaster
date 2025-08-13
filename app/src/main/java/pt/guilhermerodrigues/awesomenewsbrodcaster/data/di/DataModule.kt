package pt.guilhermerodrigues.awesomenewsbrodcaster.data.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.dsl.module
import pt.guilhermerodrigues.awesomenewsbrodcaster.apiKey
import pt.guilhermerodrigues.awesomenewsbrodcaster.data.ArticlesApi
import pt.guilhermerodrigues.awesomenewsbrodcaster.data.ArticlesRepo
import pt.guilhermerodrigues.awesomenewsbrodcaster.source
import retrofit2.Retrofit

val networkModule = module {
    single {
        Json {
            ignoreUnknownKeys = true
            explicitNulls = false
            coerceInputValues = true
        }
    }

    single{ OkHttpClient.Builder().build() }

    single{
        Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .client(get())
            .addConverterFactory(get<Json>().asConverterFactory("application/json".toMediaType()))
            .build()
    }

    single<ArticlesApi>{ get<Retrofit>().create(ArticlesApi::class.java) }

    single { ArticlesRepo(get(), get(apiKey), get(source)) }
}