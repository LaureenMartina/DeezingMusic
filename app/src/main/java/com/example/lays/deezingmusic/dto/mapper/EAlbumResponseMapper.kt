package com.example.lays.deezingmusic.dto.mapper

import com.example.lays.deezingmusic.dto.EAlbumResponse
import com.example.lays.deezingmusic.model.DeezerAlbum

class EAlbumResponseMapper {
    fun map(albumResponse: EAlbumResponse): List<DeezerAlbum> {
        val albums = albumResponse.data

        return albums.map {
            DeezerAlbum(
                    it.id,
                    it.title,
                    it.coverMedium,
                    it.nbTracks,
                    it.tracklist,
                    it.artist.id,
                    it.artist.name
            )
        }
    }
}