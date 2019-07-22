package com.example.lays.deezingmusic

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.lays.deezingmusic.services.PlayerService

class NotificationBroadcast : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        if (intent.action == NotfificationDisplayer.NOTIFY_PLAY) {
            context.stopService(Intent(context, PlayerService::class.java))
            val intent = Intent(context, PlayerService::class.java)
            context.startService(intent)

        }
    }
}