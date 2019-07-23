package com.example.lays.deezingmusic.api

import com.example.lays.deezingmusic.dto.EAlbumResponse
import com.example.lays.deezingmusic.dto.ETrackResponse
import com.example.lays.deezingmusic.dto.mapper.EAlbumResponseMapper
import com.example.lays.deezingmusic.dto.mapper.ETrackResponseMapper
import com.example.lays.deezingmusic.model.DeezerAlbum
import com.example.lays.deezingmusic.model.DeezerTrack
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object DeezerProvider {
    private val APPLICATION_ID = "355924"
    private var service: DeezerService

    init {
        service = Retrofit.Builder().baseUrl("https://api.deezer.com/")
                .client(createOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(DeezerService::class.java)
    }

    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor {
                    val request = it.request()
                    val url = request.url()

                    val urlBuilder = url.newBuilder()
                            .addQueryParameter("api_key", "ed04bc595643dec5f9e7cab04231abb0")
                            .addQueryParameter("format", "json")
                            .addQueryParameter("nojsoncallback", "1")

                    val newUrl = urlBuilder.build()
                    val newRequest = request.newBuilder().url(newUrl).build()
                    it.proceed(newRequest)
                }
                .build()
    }

    fun getAlbums(listener: Listener<List<DeezerAlbum>>) {
        service.getAlbumsArtist().enqueue(object : Callback<EAlbumResponse> {

            override fun onFailure(call: Call<EAlbumResponse>, t: Throwable) {
                listener.onError(t)
            }

            override fun onResponse(call: Call<EAlbumResponse>, response: Response<EAlbumResponse>) {
                response.body()?.also {

                    listener.onSuccess(EAlbumResponseMapper().map(it))
                }
            }

        })
    }

    fun getTracks(idAlbum: Int, listener: Listener<List<DeezerTrack>>) {
        service.getTracksAlbum(idAlbum).enqueue(object : Callback<ETrackResponse> {

            override fun onFailure(call: Call<ETrackResponse>, t: Throwable) {
                listener.onError(t)
            }

            override fun onResponse(call: Call<ETrackResponse>, response: Response<ETrackResponse>) {
                response.body()?.also {
                    listener.onSuccess(ETrackResponseMapper().map(it))
                }
            }

        })
    }

    interface Listener<T> {
        fun onSuccess(data: T?)
        fun onError(t: Throwable)
    }

}