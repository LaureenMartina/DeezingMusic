package com.example.lays.deezingmusic.services

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Handler
import android.os.IBinder
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.constraintlayout.widget.Group
import com.example.lays.deezingmusic.model.DeezerTrack



private var dataTrack: List<DeezerTrack>? = null
private var positionTrack: Int? = null

class PlayerService : Service() {
    private var mediaPlayer: MediaPlayer? = null
    private lateinit var mHandler: Handler
    private lateinit var mRunnable: Runnable



    var isPlay: Boolean = false
    var isAlreadyPlayedOneTimes: Boolean = false

    override fun onCreate() {

        mediaPlayer = MediaPlayer()
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.e("start command", dataTrack.toString())
        Log.e("start command pos", positionTrack.toString())
        mediaPlayer?.setDataSource(this@PlayerService, Uri.parse(positionTrack?.let { dataTrack?.get(it)?.preview }))
        mediaPlayer?.prepare()
        mediaPlayer?.start()
        isPlay = true

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

    fun onPlayPause() {

        if (isPlay) { // playing the song
            //mediaPlayer.seekTo(0)
            mediaPlayer?.pause()

            isPlay = false

            Toast.makeText(this, "media paused", Toast.LENGTH_SHORT).show()
        } else {

            isPlay = true
            mediaPlayer?.start()
            Toast.makeText(this, "media playing", Toast.LENGTH_SHORT).show()
        }
    }

    fun onForward(data: List<DeezerTrack>, position: Int) {
        Log.e("Data in forward", data.toString())
        Log.e("Position in forward", position.toString())
        Log.e("data size in forward", data.size.toString())

        if(position >= data.size) {
            //mediaPlayer?.reset()
            mediaPlayer?.setDataSource(this, Uri.parse(data.get(0).preview))
            mediaPlayer?.prepare()
            mediaPlayer?.start()
        } else {
            //mediaPlayer?.reset()
            mediaPlayer?.setDataSource(this, Uri.parse(data.get(position+1).preview))
            mediaPlayer?.prepare()
            mediaPlayer?.start()
        }

    }

    fun onRewind(data: List<DeezerTrack>, position: Int) {

        //mediaPlayer = MediaPlayer()
        dataTrack = data
        positionTrack = position
        positionTrack?.let {
            if(it > 0) {
                positionTrack = it-1
            }
        }
        mediaPlayer?.reset()
        mediaPlayer?.setDataSource(this, Uri.parse(positionTrack?.let { dataTrack?.get(it)?.preview }))
        mediaPlayer?.prepare()
        mediaPlayer?.start()
    }

}