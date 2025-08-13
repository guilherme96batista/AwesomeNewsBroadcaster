package pt.guilhermerodrigues.awesomenewsbroadcaster.ui.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pt.guilhermerodrigues.awesomenewsbroadcaster.domain.Article

@Composable
fun ArticlesList(
    articleDtos: List<Article>,
    onClick: (Article) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            items = articleDtos,
            key = { it.title }
        ) { article ->
            ArticleRow(article = article, onClick = { onClick(article) })
        }
    }
}