package com.example.lays.deezingmusic

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Group
import com.example.lays.deezingmusic.model.DeezerTrack
lateinit var dataTrack: List<DeezerTrack>
var positionTrack: Int? = null

class MainActivity : AppCompatActivity(), IMain {
    var isPlay: Boolean = false
    var isAlreadyPlayedOneTimes: Boolean = false
    override fun startPlayer(data: List<DeezerTrack>, position: Int) {
        dataTrack = data
        positionTrack = position
        if(!isAlreadyPlayedOneTimes) {

            isAlreadyPlayedOneTimes = true
            playerBarGroup.visibility = View.VISIBLE

        }
        Log.d("TAG : Liste tracks", data.toString())
        Log.d("TAG : position tracks", position.toString())
        Log.d("TAG : track at pos : ", position.toString())
        Log.d("TAG : position tracks", dataTrack.get(positionTrack!!).preview)
        Log.d("TAG : tracks lliste at ", dataTrack.get(0).toString())
        if(isPlay){
            mediaPlayer.reset()
        }
        mediaPlayer.setDataSource(this, Uri.parse(dataTrack.get(positionTrack!!).preview))
        mediaPlayer.prepare()
        mediaPlayer.start()
        isPlay = true
    }

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var runnable:Runnable
    private var handler: Handler = Handler()
    private var pause:Boolean = false
    lateinit var playPauseBtn: ImageView
    lateinit var forwardBtn: ImageView
    lateinit var rewindBtn: ImageView
    lateinit var playerBarGroup: Group

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mediaPlayer = MediaPlayer()
        playPauseBtn = findViewById(R.id.play_imv)
        forwardBtn = findViewById(R.id.forward_imv)
        rewindBtn = findViewById(R.id.rewind_imv)
        playerBarGroup = findViewById(R.id.player_bar_group)
        playerBarGroup.visibility = View.GONE
        // Start the media player
        playPauseBtn.setOnClickListener {
            if (isPlay) { // son en cours
                //mediaPlayer.seekTo(0)
                mediaPlayer.pause()

                isPlay = false

                Toast.makeText(this, "media paused", Toast.LENGTH_SHORT).show()
            } else {

                isPlay = true
                mediaPlayer.start()
                Toast.makeText(this, "media playing", Toast.LENGTH_SHORT).show()
            }

            mediaPlayer.setOnCompletionListener {
                Toast.makeText(this, "end", Toast.LENGTH_SHORT).show()
            }
        }
        forwardBtn.setOnClickListener {
            positionTrack?.let {
                if(it > dataTrack.size) {
                    positionTrack = 0
                } else {
                    positionTrack = it+1
                }
            }
            updatePlayer()
        }

        rewindBtn.setOnClickListener {
            positionTrack?.let {
                if(it > 0) {
                    positionTrack = it-1
                }
            }
            updatePlayer()
        }
    }

    private fun updatePlayer() {
        mediaPlayer.reset()
        mediaPlayer.setDataSource(this, Uri.parse(dataTrack.get(positionTrack!!).preview))
        mediaPlayer.prepare()
        mediaPlayer.start()
    }


}