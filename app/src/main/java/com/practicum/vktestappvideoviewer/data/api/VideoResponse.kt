package com.practicum.vktestappvideoviewer.data.api

import com.practicum.vktestappvideoviewer.domain.models.Video

data class VideoResponse(
    val hits: List<Video>,
    val pages: Int,
    val page: Int,
)