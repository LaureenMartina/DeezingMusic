package com.example.lays.deezingmusic

import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
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
import com.example.lays.deezingmusic.services.PlayerService

class MainActivity : AppCompatActivity(), IMain{
    private var isPlay: Boolean = false
    var isAlreadyPlayedOneTimes: Boolean = false

    private lateinit var playerService: PlayerService

    private var handler: Handler = Handler()
    private var pause:Boolean = false
    lateinit var playPauseBtn: ImageView
    lateinit var forwardBtn: ImageView
    lateinit var rewindBtn: ImageView
    lateinit var playerBarGroup: Group

    private var dataTrack: List<DeezerTrack>? = null
    private var positionTrack: Int? = null

    private var notificationManager: NotificationManager? = null
     lateinit var notificationBroadcast: NotificationBroadcast

    override fun showPlayerBar() {
        playerBarGroup.visibility = View.VISIBLE
    }

    override fun initPlayer(data: List<DeezerTrack>, position: Int) {
        dataTrack = data
        positionTrack = position

        val intent = Intent(this@MainActivity, PlayerService::class.java)
        //playerService.onCreate()
        //playerService.onDestroy()

        stopService(intent)
        startService(intent)
        isPlay = true

        NotfificationDisplayer.launchNotif(applicationContext,
            "test", "test2",  R.drawable.ic_album_foreground)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            notificationManager =
                getSystemService(
                    Context.NOTIFICATION_SERVICE) as NotificationManager

            NotfificationDisplayer.createNotificationChannel("player",
                "Deezing Music",
                "THIS IS AN ANDROID PLAYER FOR DEEZER")

            NotfificationDisplayer.launchNotifManager(applicationContext)

            notificationBroadcast = NotificationBroadcast()

            registerReceiver(notificationBroadcast, IntentFilter("play"))
            registerReceiver(notificationBroadcast, IntentFilter("forward"))
            registerReceiver(notificationBroadcast, IntentFilter("rewind"))
        }

        playerService = PlayerService()
        playPauseBtn = findViewById(R.id.play_imv)
        forwardBtn = findViewById(R.id.forward_imv)
        rewindBtn = findViewById(R.id.rewind_imv)
        playerBarGroup = findViewById(R.id.player_bar_group)
        playerBarGroup.visibility = View.GONE
        // Start the media player
        playPauseBtn.setOnClickListener {

            val intent = Intent(this@MainActivity, PlayerService::class.java)
            //playerService.onCreate()
            //playerService.onDestroy()
            if(isPlay) {
                isPlay = false
                stopService(intent)
            } else {
                isPlay = true
                stopService(intent)
                startService(intent)
            }
        }
        forwardBtn.setOnClickListener {
            dataTrack?.let { playerService.onForward() }
            val intent = Intent(this@MainActivity, PlayerService::class.java)
            stopService(intent)
            startService(intent)
        }

        rewindBtn.setOnClickListener {
            dataTrack?.let { playerService.onRewind() }
            val intent = Intent(this@MainActivity, PlayerService::class.java)
            stopService(intent)
            startService(intent)
        }
    }



}