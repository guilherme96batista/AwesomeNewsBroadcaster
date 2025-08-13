package pt.guilhermerodrigues.awesomenewsbroadcaster

import org.koin.core.qualifier.named
import org.koin.dsl.module

val apiKey = named("apiKey")
val source = named("newsSource")

val configModule = module {
    single(apiKey) {
        BuildConfig.news_api_key
    }

    single(source) {
        BuildConfig.news_source
    }
}