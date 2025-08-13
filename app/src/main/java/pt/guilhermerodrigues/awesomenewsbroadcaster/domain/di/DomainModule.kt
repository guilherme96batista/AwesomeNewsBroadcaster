package pt.guilhermerodrigues.awesomenewsbroadcaster.domain.di

import org.koin.dsl.module
import pt.guilhermerodrigues.awesomenewsbroadcaster.domain.GetTopArticlesUseCase

val domainModule = module {
    factory { GetTopArticlesUseCase(get()) }
}