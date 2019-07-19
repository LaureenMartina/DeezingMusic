package com.example.lays.deezingmusic.dto.mapper

import com.example.lays.deezingmusic.dto.ETrack
import com.example.lays.deezingmusic.model.DeezerTrack

class ETrackMapper {
    fun map(track: ETrack): DeezerTrack {
        return track.run {

            DeezerTrack(
                id,
                title,
                duration,
                preview
            )
        }
    }
}