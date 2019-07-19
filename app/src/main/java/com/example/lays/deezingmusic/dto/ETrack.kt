package com.example.lays.deezingmusic.dto

import com.google.gson.annotations.SerializedName

data class ETrack (
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("duration")
    val duration: Int,
    @SerializedName("preview")
    val preview: String
    )