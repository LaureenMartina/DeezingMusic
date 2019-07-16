package com.example.lays.deezingmusic.album

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.lays.deezingmusic.R
import com.example.lays.deezingmusic.model.DeezerAlbum


class AlbumAdapter : RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    private var data: List<DeezerAlbum>? = null
    private var listener: ClickListener? = null

    fun setData(data: List<DeezerAlbum>) {
        this.data = data
        notifyDataSetChanged()
    }

    fun setListener(listener: ClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.album_item, parent, false)
        return AlbumViewHolder(view)
    }

    override fun getItemCount() = data?.size ?: 0

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val deezerAlbum = data!![position]
        holder.albumTitle.text = deezerAlbum.title

        Glide.with(holder.itemView)
                .load(deezerAlbum.tracklist)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.albumCover)

        holder.itemView.setOnClickListener { listener?.onClick(deezerAlbum) }
    }


    class AlbumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val artistAlbum: TextView = itemView.findViewById(R.id.item_album_artist)
        val albumCover: ImageView = itemView.findViewById(R.id.item_album_cover)
        val albumTitle: TextView = itemView.findViewById(R.id.item_album_title)
    }

    interface ClickListener {
        fun onClick(photo: DeezerAlbum)
    }
}
