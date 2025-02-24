package com.practicum.vktestappvideoviewer.domain.video

import com.practicum.vktestappvideoviewer.data.api.VideoResponse
import kotlinx.coroutines.flow.Flow

interface VideoInteractor {
    suspend fun getVideos(lookupCache: Boolean): Flow<Result<VideoResponse>>
}