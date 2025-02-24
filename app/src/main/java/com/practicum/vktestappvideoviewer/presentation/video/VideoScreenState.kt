package com.practicum.vktestappvideoviewer.presentation.video

import com.practicum.vktestappvideoviewer.data.api.VideoResponse

sealed interface VideoScreenState{
    data class Content(
        val videos: VideoResponse
    ): VideoScreenState

    data object Error: VideoScreenState
    data object Loading: VideoScreenState
}