package com.example.lays.deezingmusic.services

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.IBinder
import android.util.Log
import com.example.lays.deezingmusic.model.DeezerTrack



private var dataTrack: List<DeezerTrack>? = null
private var positionTrack: Int? = null

class PlayerService : Service() {
    private var mediaPlayer: MediaPlayer? = null


    var isPlay: Boolean = false

    override fun onCreate() {
        mediaPlayer = MediaPlayer()
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.e("start command", dataTrack.toString())
        Log.e("start command pos", positionTrack.toString())
        positionTrack?.let {
            if (it >= 0) {
                mediaPlayer?.setDataSource(this@PlayerService, Uri.parse(positionTrack?.let { dataTrack?.get(it)?.preview }))
                mediaPlayer?.prepare()
                mediaPlayer?.start()
                isPlay = true
            }
        }

        return Service.START_STICKY
    }


    override fun onBind(intent: Intent?): IBinder? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.stop()
    }


     fun initPlayer(data: List<DeezerTrack>, position: Int) {
         dataTrack = data
         positionTrack = position


    }

    fun onForward() {
        positionTrack?.let {
            dataTrack?.let { deezerList ->
                if(it >= deezerList.size) {
                    positionTrack = 0
                } else {
                    positionTrack = it+1
                }
            }
        }

    }

    fun onRewind() {
        positionTrack?.let {
            dataTrack?.let { deezerList ->
                if(it >= deezerList.size) {
                    positionTrack = 0
                } else {
                    positionTrack = it-1
                }
            }
        }
    }

}