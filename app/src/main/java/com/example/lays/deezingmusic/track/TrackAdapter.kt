package com.example.lays.deezingmusic.track

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lays.deezingmusic.R
import com.example.lays.deezingmusic.model.DeezerAlbum
import com.example.lays.deezingmusic.model.DeezerTrack
import com.squareup.picasso.Picasso

class TrackAdapter : RecyclerView.Adapter<TrackAdapter.TrackViewHolder>(){

    private var data: List<DeezerTrack>? = null
    private var listener: ClickListener? = null

    fun setData(data: List<DeezerTrack>) {
        this.data = data
        notifyDataSetChanged()
    }

    fun setListener(listener: ClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.track_item, parent, false)
        return TrackViewHolder(view)

    }

    override fun getItemCount() = data?.size ?: 0

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        val deezerTrack = data!![position]
        holder.trackTitle.text = deezerTrack.title



        holder.itemView.setOnClickListener { listener?.onClick(deezerTrack) }
    }

    class TrackViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val trackTitle: TextView = itemView.findViewById(R.id.item_track_title)
    }

    interface ClickListener {
        fun onClick(track: DeezerTrack)
    }

}