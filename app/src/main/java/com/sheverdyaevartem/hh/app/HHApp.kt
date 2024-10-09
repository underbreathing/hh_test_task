package com.sheverdyaevartem.hh.app

import android.app.Application
import com.sheverdyaevartem.hh.di.dataModule
import com.sheverdyaevartem.hh.di.interactorModule
import com.sheverdyaevartem.hh.di.repositoryModule
import com.sheverdyaevartem.hh.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class HHApp : Application() {


    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@HHApp)
            modules(dataModule, repositoryModule, interactorModule, viewModelModule)
        }
    }
}