package com.example.lays.deezingmusic.api

import com.example.lays.deezingmusic.dto.EAlbumResponse
import com.example.lays.deezingmusic.dto.ETrackResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DeezerService {

    @GET("user/2529/albums/")
    fun getAlbumsArtist(
            //@Query("nameArtist")
            //nameArtist: String
    ): Call<EAlbumResponse>

    @GET("album/{id}/tracks")
    fun getTracksAlbum(
        @Path("id") id: Int
    ): Call<ETrackResponse>
}