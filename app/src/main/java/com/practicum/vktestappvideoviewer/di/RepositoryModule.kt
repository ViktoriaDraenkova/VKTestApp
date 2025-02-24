package com.practicum.vktestappvideoviewer.di

import com.practicum.vktestappvideoviewer.data.video.VideoRepository
import com.practicum.vktestappvideoviewer.data.video.VideoRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    single<VideoRepository> {
        VideoRepositoryImpl(get(), get())
    }
}