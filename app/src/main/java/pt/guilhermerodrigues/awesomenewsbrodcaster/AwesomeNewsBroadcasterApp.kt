package pt.guilhermerodrigues.awesomenewsbrodcaster

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import pt.guilhermerodrigues.awesomenewsbrodcaster.data.di.networkModule
import pt.guilhermerodrigues.awesomenewsbrodcaster.domain.di.domainModule
import pt.guilhermerodrigues.awesomenewsbrodcaster.ui.presentation.di.uiModule

class AwesomeNewsBroadcasterApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AwesomeNewsBroadcasterApp)
            modules(networkModule, uiModule, domainModule, configModule)
        }
    }
}