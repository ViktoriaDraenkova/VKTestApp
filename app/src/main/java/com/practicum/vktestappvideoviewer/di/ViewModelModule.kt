package com.practicum.vktestappvideoviewer.di

import com.practicum.vktestappvideoviewer.presentation.video.VideoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        VideoViewModel(get())
    }
}