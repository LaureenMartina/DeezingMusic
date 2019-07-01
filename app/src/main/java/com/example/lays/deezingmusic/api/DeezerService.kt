package com.example.lays.deezingmusic.api

import com.example.lays.deezingmusic.dto.EAlbumResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DeezerService {
    //@GET("/album?q=eminem")
    //fun getAlbumsFromArtist(/*@Query("name_artist") userId: String*/): Call<EAlbumResponseMapper>

    @GET("/album?q=")
    fun getAlbumsArtist(
            @Query("nameArtist")
            nameArtist: String
    ): Call<EAlbumResponse>
}