package com.practicum.vktestappvideoviewer.ui.video

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.fragment.app.Fragment
import com.practicum.vktestappvideoviewer.databinding.VideoDetailsFragmentBinding
import java.net.URI

class VideoDetailsFragment : Fragment() {

    private var _binding: VideoDetailsFragmentBinding? = null
    private val binding get(): VideoDetailsFragmentBinding = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = VideoDetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var videoUrl = ""
        arguments?.let {
            videoUrl = VideoDetailsFragmentArgs.fromBundle(it).video
        }
        val mediaController = MediaController(requireContext())
        mediaController.setAnchorView(binding.videoView)

        val uri = Uri.parse(videoUrl)
        binding.videoView.setMediaController(mediaController)
        binding.videoView.setVideoURI(uri)
        binding.videoView.requestFocus()
        binding.videoView.start()

    }
}