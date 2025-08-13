package pt.guilhermerodrigues.awesomenewsbroadcaster.ui.presentation

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import pt.guilhermerodrigues.awesomenewsbroadcaster.core.AsyncState
import pt.guilhermerodrigues.awesomenewsbroadcaster.domain.Article
import pt.guilhermerodrigues.awesomenewsbroadcaster.ui.presentation.components.ArticlesList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticlesScreen(
    modifier: Modifier,
    state: AsyncState<List<Article>>,
    onRefresh: () -> Unit,
    onArticleClick: (Article) -> Unit
) {
    val isRefreshing = state is AsyncState.Loading

    PullToRefreshBox(
        modifier = modifier,
        isRefreshing = isRefreshing,
        onRefresh = onRefresh
    ) {
        when(val state = state){
            AsyncState.Loading -> {
                CircularProgressIndicator()
            }
            is AsyncState.Error -> {
                Text("Erro: ${state.throwable.message}")
            }
            is AsyncState.Success -> {
                ArticlesList(
                    articleDtos = state.data,
                    onClick = onArticleClick
                )
            }
        }
    }
}