package pt.guilhermerodrigues.awesomenewsbrodcaster.ui.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import pt.guilhermerodrigues.awesomenewsbrodcaster.domain.Article
import pt.guilhermerodrigues.awesomenewsbrodcaster.ui.ArticlesViewModel

@Composable
fun ArticlesRoute(
    modifier: Modifier,
    onArticleClick: (Article) -> Unit
) {
    val vm: ArticlesViewModel = koinViewModel()
    val state by vm.state.collectAsStateWithLifecycle()

    ArticlesScreen(
        modifier = modifier,
        state = state,
        onRefresh = vm::refresh,
        onArticleClick = onArticleClick
    )
}

@Composable
fun ArticleRoute(
    modifier: Modifier,
    article: Article
) {
    ArticleScreen(
        modifier = modifier,
        article = article
    )
}