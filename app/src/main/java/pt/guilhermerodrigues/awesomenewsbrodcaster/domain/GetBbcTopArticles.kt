package pt.guilhermerodrigues.awesomenewsbrodcaster.domain

import pt.guilhermerodrigues.awesomenewsbrodcaster.core.UseCase
import pt.guilhermerodrigues.awesomenewsbrodcaster.data.Article

class GetBbcTopArticles: UseCase<Unit, Result<List<Article>>>() {
    override suspend fun invoke(params: Unit): Result<List<Article>> {
        TODO("Not yet implemented")
    }
}