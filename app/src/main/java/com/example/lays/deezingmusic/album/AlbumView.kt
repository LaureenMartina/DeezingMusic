package com.example.lays.deezingmusic.album

interface AlbumView: BaseView {
    fun updateData(data: List<AlbumModel>)
}