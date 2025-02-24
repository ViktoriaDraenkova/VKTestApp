package com.practicum.vktestappvideoviewer.presentation.video

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.practicum.vktestappvideoviewer.R
import com.practicum.vktestappvideoviewer.databinding.VideoViewSmallBinding
import com.practicum.vktestappvideoviewer.domain.models.Video

class VideoViewAdapter(
    private val clickListener: OnClickListener,
) : RecyclerView.Adapter<VideoViewAdapter.ViewHolder>() {
    private var videos = mutableListOf<Video>()

    fun setList(list: List<Video>) {
        this.videos.clear()
        this.videos.addAll(list)
        notifyDataSetChanged()
    }

    fun addItems(newVideos: List<Video>) {
        val startPosition = videos.size
        videos.addAll(newVideos)
        notifyItemRangeInserted(startPosition, newVideos.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            VideoViewSmallBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(
            binding = binding,
            clickListener = clickListener,
        )
    }

    override fun getItemCount(): Int = videos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(videos[position])
    }

    class ViewHolder(
        private val binding: VideoViewSmallBinding,
        private val clickListener: OnClickListener,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(video: Video) {
            binding.videoName.text = video.videoName
            binding.duration?.text = secondsToHumanReadable(video.duration)

            Glide.with(itemView)
                .load(video.thumbnail)
                .placeholder(R.drawable.placeholder)
                .centerCrop()
                .into(binding.videoPreview)

            itemView.setOnClickListener { clickListener.onVideoClick(video) }
        }

        private fun secondsToHumanReadable(numb:Float):String{
            val int = numb.toInt()
            val min = int/60
            val sec = int%60
            return "%02d:%02d".format(min, sec)
        }
    }

    fun interface OnClickListener {
        fun onVideoClick(video: Video)
    }

}
