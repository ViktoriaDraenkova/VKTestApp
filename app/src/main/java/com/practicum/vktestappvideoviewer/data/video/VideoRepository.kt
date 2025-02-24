package com.practicum.vktestappvideoviewer.data.video

import com.practicum.vktestappvideoviewer.data.api.VideoResponse
import kotlinx.coroutines.flow.Flow

interface VideoRepository {
    suspend fun getVideos(page: Int = 0): Flow<Result<VideoResponse>>

    suspend fun getCachedVideos(): VideoResponse?
    suspend fun cache(videoResponse: VideoResponse)
}