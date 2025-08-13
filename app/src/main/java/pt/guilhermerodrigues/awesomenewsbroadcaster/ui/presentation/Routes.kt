package pt.guilhermerodrigues.awesomenewsbroadcaster.ui.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import pt.guilhermerodrigues.awesomenewsbroadcaster.domain.Article
import pt.guilhermerodrigues.awesomenewsbroadcaster.ui.ArticlesViewModel

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