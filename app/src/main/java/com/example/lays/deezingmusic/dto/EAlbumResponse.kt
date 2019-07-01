package com.example.lays.deezingmusic.dto

import com.google.gson.annotations.SerializedName

data class EAlbumResponse(
        @SerializedName("data")
        val data: List<EAlbum>,
        @SerializedName("next")
        val next: String,
        @SerializedName("total")
        val total: Int
)