package com.practicum.vktestappvideoviewer.presentation.video

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practicum.vktestappvideoviewer.domain.video.VideoInteractor
import kotlinx.coroutines.launch
import java.lang.invoke.MethodHandles.Lookup

class VideoViewModel(private  val videoInteractor: VideoInteractor): ViewModel() {
     private val stateLiveData = MutableLiveData<VideoScreenState>()
     fun getStateLiveData(): LiveData<VideoScreenState> = stateLiveData

     fun refreshVideos(lookupCache: Boolean) {
          Log.d("refreshVideosCache", lookupCache.toString())
          stateLiveData.value = VideoScreenState.Loading
          viewModelScope.launch {
               videoInteractor.getVideos(lookupCache).collect {
                    if (it.isSuccess) {
                         stateLiveData.value = VideoScreenState.Content(it.getOrThrow())
                    } else {
                         stateLiveData.value = VideoScreenState.Error
                    }
               }
          }
     }
}