package com.practicum.vktestappvideoviewer.ui.video

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.practicum.vktestappvideoviewer.databinding.VideoListFragmentBinding
import com.practicum.vktestappvideoviewer.presentation.video.VideoScreenState
import com.practicum.vktestappvideoviewer.presentation.video.VideoViewAdapter
import com.practicum.vktestappvideoviewer.presentation.video.VideoViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class VideoListFragment : Fragment() {
    private val viewModel: VideoViewModel by viewModel()
    private var _binding: VideoListFragmentBinding? = null
    private val binding get(): VideoListFragmentBinding = _binding!!
    private var videoViewAdapter: VideoViewAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = VideoListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        videoViewAdapter = VideoViewAdapter {
            val action =
                VideoListFragmentDirections.actionVideoListFragmentToVideoDetailsFragment(it.urls.mp4)
            findNavController().navigate(action)
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = videoViewAdapter
        }

        viewModel.getStateLiveData().observe(viewLifecycleOwner) { screenState ->
            hideAll()
            when (screenState) {
                is VideoScreenState.Loading -> showLoading()
                is VideoScreenState.Error -> showError()
                is VideoScreenState.Content -> {
                    videoViewAdapter!!.setList(screenState.videos.hits)
                    showContent()
                    binding.swipeUpRefreshLayout.isRefreshing = false
                }
            }
        }

        viewModel.refreshVideos(lookupCache = true)

        binding.swipeUpRefreshLayout.setOnRefreshListener {
            viewModel.refreshVideos(lookupCache = false)
        }
    }

//    override fun onResume() {
//        super.onResume()
//        viewModel.refreshVideos(lookupCache = true)
//    }

    private fun showError() {
        binding.errorNoInternetOrData.visibility = View.VISIBLE
    }

    private fun showLoading() {
        binding.loading.visibility = View.VISIBLE
    }

    private fun showContent() {
        binding.recyclerView.visibility = View.VISIBLE
    }

    private fun hideAll() {
        binding.recyclerView.visibility = View.GONE
        binding.loading.visibility = View.GONE
        binding.errorNoInternetOrData.visibility = View.GONE
    }
}