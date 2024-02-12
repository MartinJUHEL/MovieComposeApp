package com.example.moviecomposeapp.data.mapper

import com.example.moviecomposeapp.data.dto.VideoResponseDto
import com.example.moviecomposeapp.domain.model.YoutubeVideo
import javax.inject.Inject

class VideoResponseDtoToYoutubeVideoMapper @Inject constructor() {
    fun execute(response: VideoResponseDto): YoutubeVideo? {
        return response.results.firstOrNull {
            it.site == YOUTUBE && it.type == TRAILER
        }?.toYoutubeVideo()
    }

    private fun VideoResponseDto.VideoDto.toYoutubeVideo(): YoutubeVideo {
        return YoutubeVideo(
            name = name,
            youtubeId = key
        )
    }

    ///////////////////////////////////////////////////////////////
    //CONSTANTS
    ///////////////////////////////////////////////////////////////

    companion object {
        private const val YOUTUBE = "YouTube"
        private const val TRAILER = "Trailer"
    }
}