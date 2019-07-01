package com.example.lays.deezingmusic.album

data class AlbumModel(
        val id: Int,
        val title: String,
        val link: String,
        val cover: String,
        val nb_track: Int,
        val tracklist: String
)