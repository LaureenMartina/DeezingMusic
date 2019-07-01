package com.example.lays.deezingmusic.dto.mapper

import com.example.lays.deezingmusic.dto.EAlbum
import com.example.lays.deezingmusic.model.DeezerAlbum

class EAlbumMapper {
    fun map(album: EAlbum): DeezerAlbum {
        return album.run {

            DeezerAlbum(
                    id,
                    title,
                    coverMedium,
                    nbTracks,
                    tracklist,
                    artist.id,
                    artist.name
            )
        }
    }
}