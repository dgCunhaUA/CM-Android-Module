package pt.ua.cm.dialer

import android.app.Application
import timber.log.Timber

class DialerApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}