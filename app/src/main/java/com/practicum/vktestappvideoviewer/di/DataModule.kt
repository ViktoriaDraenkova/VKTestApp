package com.practicum.vktestappvideoviewer.di

import androidx.activity.ComponentActivity
import com.practicum.vktestappvideoviewer.data.api.VideosApi
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    single<VideosApi> {
        Retrofit.Builder()
            .baseUrl("https://api.coverr.co")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(VideosApi::class.java)
    }

    single {
        androidContext().getSharedPreferences(
            "VIDEO_COLLECTION_PREFS",
            ComponentActivity.MODE_PRIVATE
        )
    }

}
