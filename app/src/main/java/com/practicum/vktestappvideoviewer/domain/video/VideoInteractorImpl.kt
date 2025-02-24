package com.practicum.vktestappvideoviewer.domain.video

import com.practicum.vktestappvideoviewer.data.api.VideoResponse
import com.practicum.vktestappvideoviewer.data.video.VideoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class VideoInteractorImpl(private val repository: VideoRepository) : VideoInteractor {
    override suspend fun getVideos(lookupCache: Boolean): Flow<Result<VideoResponse>> = flow {
        val cached = if (lookupCache) repository.getCachedVideos() else null
        if (cached != null) {
            pages = cached.pages
            page = (cached.page + 1) % pages
            emit(Result.success(cached))
        } else {
            repository.getVideos(page).collect {
                if (it.isSuccess) {
                    pages = it.getOrNull()!!.pages
                    page = (page + 1) % pages
                    repository.cache(it.getOrThrow())
                }
                emit(it)
            }
        }
    }

    companion object {
        var page = 0
        var pages = 1
    }
}