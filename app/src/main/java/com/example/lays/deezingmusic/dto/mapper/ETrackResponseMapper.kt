package com.example.lays.deezingmusic.dto.mapper

import com.example.lays.deezingmusic.dto.ETrackResponse
import com.example.lays.deezingmusic.model.DeezerTrack

class ETrackResponseMapper {
    fun map(trackResponse: ETrackResponse?): List<DeezerTrack>? {
        val tracks = trackResponse?.data

        return tracks?.map {
            DeezerTrack(
                it.id,
                it.title,
                it.duration,
                it.preview
            )
        }
    }
}