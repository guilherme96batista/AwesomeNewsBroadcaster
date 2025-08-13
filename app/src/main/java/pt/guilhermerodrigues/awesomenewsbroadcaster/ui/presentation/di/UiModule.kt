package pt.guilhermerodrigues.awesomenewsbroadcaster.ui.presentation.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import pt.guilhermerodrigues.awesomenewsbroadcaster.ui.ArticlesViewModel

val uiModule = module {
    viewModelOf(::ArticlesViewModel)
}