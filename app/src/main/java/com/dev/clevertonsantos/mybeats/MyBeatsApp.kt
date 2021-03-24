package com.dev.clevertonsantos.mybeats

import android.app.Application
import com.dev.clevertonsantos.mybeats.di.apiDataSourceModule
import com.dev.clevertonsantos.mybeats.di.homeModule
import com.dev.clevertonsantos.mybeats.di.loginModule
import com.dev.clevertonsantos.mybeats.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyBeatsApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyBeatsApp)

            modules(apiDataSourceModule, loginModule, homeModule, networkModule)
        }
    }

}