package com.example.lays.deezingmusic.model

data class DeezerAlbum(
        val id: Int,
        val title: String,
        val coverMedium: String?,
        val nbTracks: Int,
        val tracklist: String,
        val artistId: Int,
        val artistName: String)