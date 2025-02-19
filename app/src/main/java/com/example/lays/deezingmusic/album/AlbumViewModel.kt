package com.example.lays.deezingmusic.album

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lays.deezingmusic.api.DeezerProvider
import com.example.lays.deezingmusic.model.DeezerAlbum
import com.example.lays.deezingmusic.model.DeezerTrack

class AlbumViewModel : ViewModel() {
    private val albumMutableLiveData = MutableLiveData<List<DeezerAlbum>>()
    val albumLiveData: LiveData<List<DeezerAlbum>> = albumMutableLiveData

    fun getDeezerAlbums() {
        DeezerProvider.getAlbums(object : DeezerProvider.Listener<List<DeezerAlbum>> {
            override fun onSuccess(data: List<DeezerAlbum>?) {
                albumMutableLiveData.value = data
            }

            override fun onError(t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}