package com.example.lays.deezingmusic.album

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.lays.deezingmusic.api.DeezerProvider
import com.example.lays.deezingmusic.model.DeezerAlbum

class AlbumViewModel : ViewModel() {
    private val albumMutableLiveData = MutableLiveData<List<DeezerAlbum>>()
    val albumLiveData: LiveData<List<DeezerAlbum>> = albumMutableLiveData

    fun getDeezerAlbums(nameArtist: String) {
        //albumMutableLiveData.setLoadingState(true)
        DeezerProvider.getAlbums(nameArtist, object : DeezerProvider.Listener<List<DeezerAlbum>> {
            override fun onSuccess(data: List<DeezerAlbum>) {
                //albumMutableLiveData.setLoadingState(false)
                //albumMutableLiveData.value = Success(data)
                albumMutableLiveData.value = data
            }

            override fun onError(t: Throwable) {
                //albumMutableLiveData.setLoadingState(false)
                //albumMutableLiveData.value = Failure(t)
                t.printStackTrace()
            }
        })
    }
}