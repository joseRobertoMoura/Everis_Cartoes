package com.example.everis_cartoes

import android.app.Application
import com.example.everis_cartoes.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class EverisCartoesApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@EverisCartoesApplication)

            modules(appModule)
        }
    }
}