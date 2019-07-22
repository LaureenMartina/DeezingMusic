package com.example.lays.deezingmusic

import com.example.lays.deezingmusic.model.DeezerTrack
import java.text.FieldPosition

interface IMain {
    fun showPlayerBar()
    fun initPlayer(data: List<DeezerTrack>, position: Int)
}