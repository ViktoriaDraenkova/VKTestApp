package com.practicum.vktestappvideoviewer.di

import com.practicum.vktestappvideoviewer.domain.video.VideoInteractor
import com.practicum.vktestappvideoviewer.domain.video.VideoInteractorImpl
import org.koin.dsl.module

val interactorModule = module {
    single<VideoInteractor> {
        VideoInteractorImpl(get())
    }
}