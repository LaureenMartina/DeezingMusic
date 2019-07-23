package com.example.lays.deezingmusic

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.lays.deezingmusic.services.PlayerService

class NotificationBroadcast : BroadcastReceiver() {

    var isPlay = true
    var playerService: PlayerService? = null
    override fun onReceive(context: Context, intent: Intent) {
        playerService = PlayerService()
        if (intent.action == NotfificationDisplayer.NOTIFY_PLAY) {
            context.stopService(Intent(context, PlayerService::class.java))
            val intent = Intent(context, PlayerService::class.java)
            if(isPlay) {
                context.stopService(intent)
                isPlay = false
            } else {
                context.stopService(intent)
                context.startService(intent)
                isPlay = true
            }
        }

        if (intent.action == NotfificationDisplayer.NOTIFY_FORWARD) {
            val intent = Intent(context, PlayerService::class.java)
            playerService?.onForward()
            context.stopService(intent)
            context.startService(intent)
        }

        if (intent.action == NotfificationDisplayer.NOTIFY_REWIND) {
            val intent = Intent(context, PlayerService::class.java)
            playerService?.onForward()
            context.stopService(intent)
            context.startService(intent)
        }
    }
}