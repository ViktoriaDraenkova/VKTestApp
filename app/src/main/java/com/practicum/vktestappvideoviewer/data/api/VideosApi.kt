package com.practicum.vktestappvideoviewer.data.api

import com.practicum.vktestappvideoviewer.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface VideosApi{
    @GET("/videos?query=cats&urls=true")
    @Headers("Authorization: Bearer ${BuildConfig.COVERR_API_KEY}")
    suspend fun get(@Query("page") page: Int = 0): VideoResponse
}

