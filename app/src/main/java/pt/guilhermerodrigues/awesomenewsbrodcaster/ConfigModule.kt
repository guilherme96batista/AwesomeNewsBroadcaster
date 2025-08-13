package pt.guilhermerodrigues.awesomenewsbrodcaster

import org.koin.core.qualifier.named
import org.koin.dsl.module

val apiKey = named("apiKey")

val configModule = module {
    single(apiKey) {
        BuildConfig.news_api_key
    }
}