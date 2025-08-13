package pt.guilhermerodrigues.awesomenewsbroadcaster

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import pt.guilhermerodrigues.awesomenewsbroadcaster.data.di.networkModule
import pt.guilhermerodrigues.awesomenewsbroadcaster.domain.di.domainModule
import pt.guilhermerodrigues.awesomenewsbroadcaster.ui.presentation.di.uiModule

class AwesomeNewsBroadcasterApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AwesomeNewsBroadcasterApp)
            modules(networkModule, uiModule, domainModule, configModule)
        }
    }
}