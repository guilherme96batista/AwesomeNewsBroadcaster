package pt.guilhermerodrigues.awesomenewsbrodcaster.data

import coil3.ImageLoader
import coil3.disk.DiskCache
import coil3.memory.MemoryCache
import coil3.network.okhttp.OkHttpNetworkFetcherFactory
import coil3.request.CachePolicy
import coil3.util.DebugLogger
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit

val networkModule = module {
    single{ OkHttpClient.Builder().build() }
    single{
        Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .client(get())
            .build()
    }
    single{ get<Retrofit>().create(TopArticlesApi::class.java) }

    single{
        ImageLoader.Builder(androidContext())
            .components {
                add(OkHttpNetworkFetcherFactory())
            }
            .memoryCachePolicy(CachePolicy.ENABLED)
            .memoryCache {
                MemoryCache.Builder()
                    .maxSizePercent(androidContext(),0.15)
                    .strongReferencesEnabled(true)
                    .build()
            }
            .diskCachePolicy(CachePolicy.ENABLED)
            .diskCache {
                DiskCache.Builder()
                    .maxSizePercent(0.1)
                    .build()
            }
            .logger(DebugLogger())
            .build()
    }


}