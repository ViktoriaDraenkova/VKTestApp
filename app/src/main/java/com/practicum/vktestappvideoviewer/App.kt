package com.practicum.vktestappvideoviewer

import android.app.Application
import com.practicum.vktestappvideoviewer.di.dataModule
import com.practicum.vktestappvideoviewer.di.interactorModule
import com.practicum.vktestappvideoviewer.di.repositoryModule
import com.practicum.vktestappvideoviewer.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@App)
            modules(listOf( dataModule, repositoryModule, interactorModule, viewModelModule ))
        }
    }


}
 