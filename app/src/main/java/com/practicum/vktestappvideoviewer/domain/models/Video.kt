package com.practicum.vktestappvideoviewer.domain.models

import com.google.gson.annotations.SerializedName

data class Video(
    @SerializedName("video_id")
    val videoId: String,
    @SerializedName("title")
    val videoName: String,
    @SerializedName("duration")
    val duration: Float,
    val thumbnail: String,
    val urls: URLs,
)

data class URLs(
    val mp4: String,
)
