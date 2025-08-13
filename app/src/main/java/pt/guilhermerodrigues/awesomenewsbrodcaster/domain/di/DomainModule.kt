package pt.guilhermerodrigues.awesomenewsbrodcaster.domain.di

import org.koin.dsl.module
import pt.guilhermerodrigues.awesomenewsbrodcaster.domain.GetTopArticlesUseCase

val domainModule = module {
    factory { GetTopArticlesUseCase(get()) }
}