package pt.guilhermerodrigues.awesomenewsbrodcaster.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import pt.guilhermerodrigues.awesomenewsbrodcaster.core.AsyncState
import pt.guilhermerodrigues.awesomenewsbrodcaster.core.UseCase
import pt.guilhermerodrigues.awesomenewsbrodcaster.data.ArticlesRepo

class GetTopArticlesUseCase(val repo: ArticlesRepo): UseCase<Unit, Flow<AsyncState<List<Article>>>>() {
    override suspend operator fun invoke(params: Unit): Flow<AsyncState<List<Article>>> = flow {
        emit(AsyncState.Loading)
        emit(AsyncState.Success(repo.getTopArticles()))
    }.catch { e -> emit(AsyncState.Error(e)) }.flowOn(Dispatchers.IO)
}