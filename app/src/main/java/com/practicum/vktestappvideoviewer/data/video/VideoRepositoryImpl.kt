package com.practicum.vktestappvideoviewer.data.video

import android.annotation.SuppressLint
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.practicum.vktestappvideoviewer.data.api.VideoResponse
import com.practicum.vktestappvideoviewer.data.api.VideosApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class VideoRepositoryImpl(
    private val videosApi: VideosApi,
    private val sharedPreferences: SharedPreferences
) : VideoRepository {
    override suspend fun getVideos(page: Int): Flow<Result<VideoResponse>> = flow {
        try {
            emit(Result.success(videosApi.get(page)))
        } catch (err: Throwable) {
            emit(Result.failure(err))
        }
    }

    override suspend fun getCachedVideos(): VideoResponse? {
        val jsonString = sharedPreferences.getString(KEY_VIDEOS_CACHE, null)
        return if (jsonString != null) {
            Gson().fromJson(jsonString, VideoResponse::class.java)
        } else {
            null
        }
    }

    override suspend fun cache(videoResponse: VideoResponse) {
        sharedPreferences.edit().putString(KEY_VIDEOS_CACHE, Gson().toJson(videoResponse)).apply()
    }

    companion object {
        const val KEY_VIDEOS_CACHE = "cached_videos"
    }
}