package pt.guilhermerodrigues.awesomenewsbrodcaster.ui.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import coil3.ImageLoader
import coil3.SingletonImageLoader
import coil3.disk.DiskCache
import coil3.disk.directory
import coil3.memory.MemoryCache
import coil3.network.okhttp.OkHttpNetworkFetcherFactory
import coil3.request.CachePolicy
import coil3.request.crossfade
import coil3.util.DebugLogger
import okhttp3.OkHttpClient
import org.koin.compose.koinInject
import pt.guilhermerodrigues.awesomenewsbrodcaster.domain.Article
import java.io.File

@Composable
fun AppRoot() {
    var selected: Article? by remember { mutableStateOf(null) }
    val okHttp: OkHttpClient = koinInject()
    SingletonImageLoader.setSafe { context ->
        val imageDiskDir = File(context.cacheDir, "coil_image_cache")
        ImageLoader.Builder(context)
            .components {
                add(OkHttpNetworkFetcherFactory(okHttp))
            }
            .memoryCachePolicy(CachePolicy.ENABLED)
            .memoryCache {
                MemoryCache.Builder()
                    .maxSizePercent(context,0.15)
                    .strongReferencesEnabled(true)
                    .build()
            }
            .diskCachePolicy(CachePolicy.ENABLED)
            .diskCache {
                DiskCache.Builder()
                    .directory(imageDiskDir)
                    .maxSizePercent(0.1)
                    .build()
            }
            .crossfade(true)
            .logger(DebugLogger())
            .build()
    }
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        if (selected == null) {
            ArticlesRoute(
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                { article -> selected = article }
            )
        } else {
            BackHandler(enabled = true) { selected = null }

            ArticleRoute(
                modifier = Modifier.padding(innerPadding),
                article = selected!!
            )
        }
    }
}

@Preview
@Composable
fun PreviewAppRoot() {
    MaterialTheme{
        AppRoot()
    }
}