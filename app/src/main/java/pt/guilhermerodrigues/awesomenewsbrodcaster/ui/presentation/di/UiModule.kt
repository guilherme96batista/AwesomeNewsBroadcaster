package pt.guilhermerodrigues.awesomenewsbrodcaster.ui.presentation.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import pt.guilhermerodrigues.awesomenewsbrodcaster.ui.ArticlesViewModel

val uiModule = module {
    viewModelOf(::ArticlesViewModel)
}