package com.example.lays.deezingmusic.dto

import com.google.gson.annotations.SerializedName

data class ETrackResponse (
    @SerializedName("data")
    val data: List<ETrack>,
    @SerializedName("total")
    val total: Int
)