package pt.guilhermerodrigues.awesomenewsbrodcaster.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import pt.guilhermerodrigues.awesomenewsbrodcaster.core.AsyncState
import pt.guilhermerodrigues.awesomenewsbrodcaster.domain.Article
import pt.guilhermerodrigues.awesomenewsbrodcaster.domain.GetTopArticlesUseCase

class ArticlesViewModel(
    private val getArticlesUseCase: GetTopArticlesUseCase
): ViewModel() {

    private val _state = MutableStateFlow<AsyncState<List<Article>>>(AsyncState.Loading)
    val state: StateFlow<AsyncState<List<Article>>> = _state

    private var job: Job? = null


    init {
        refresh()
    }

    fun refresh() {
        job?.cancel()
        job = viewModelScope.launch {
            getArticlesUseCase(Unit).map { state ->
                when(state) {
                    is AsyncState.Success -> AsyncState.Success(state.data.sortedByDescending { it.publishedAt })
                    else -> state
                }
            }.collect {_state.value = it}
        }
    }
}