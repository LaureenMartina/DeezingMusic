package com.example.lays.deezingmusic.track

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lays.deezingmusic.api.DeezerProvider
import com.example.lays.deezingmusic.model.DeezerAlbum
import com.example.lays.deezingmusic.model.DeezerTrack

class TrackViewModel : ViewModel() {
    private val trackMutableLiveData = MutableLiveData<List<DeezerTrack>>()
    val trackLiveData: LiveData<List<DeezerTrack>> = trackMutableLiveData

    fun getDeezerTracks(idAlbum: Int) {
        DeezerProvider.getTracks(idAlbum, object : DeezerProvider.Listener<List<DeezerTrack>> {
            override fun onSuccess(data: List<DeezerTrack>) {
                trackMutableLiveData.value = data
            }

            override fun onError(t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}