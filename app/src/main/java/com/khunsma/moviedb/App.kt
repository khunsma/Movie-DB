package com.khunsma.moviedb

import android.app.Application
import com.khunsma.moviedb.di.*
import org.koin.android.ext.android.startKoin
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }

        startKoin(
            this, listOf(
                appModule, interactorModule, mapperModule, presenterModule,
                repositoryModule, networkModule, cacheModule
            )
        )
    }
}