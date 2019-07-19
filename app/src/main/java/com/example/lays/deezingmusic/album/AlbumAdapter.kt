package com.example.lays.deezingmusic.album

import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import com.example.lays.deezingmusic.R
import com.example.lays.deezingmusic.model.DeezerAlbum
import com.squareup.picasso.Picasso
import android.widget.LinearLayout


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

        /*Picasso.get()
            .load(deezerAlbum.coverMedium)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .fit()
            .into(holder.albumCover)*/

        Picasso.get()
            .load(deezerAlbum.coverMedium)
            .placeholder(R.drawable.ic_album_foreground)
            .into(holder.albumCover, object : com.squareup.picasso.Callback {
                override fun onSuccess() {
                    val bmpDrawable = holder.albumCover.drawable as BitmapDrawable
                    Palette.from(bmpDrawable.bitmap).generate {
                        it?.let { palette ->
                            //holder.itemView.context.resources.getColor(R.color.colorPrimary)
                            val color = palette.getLightMutedColor(Color.argb(8, 200, 55, 155))
                            holder.albumBackCover.setBackgroundColor(color)

                            holder.albumTitle.setTextColor(Color.BLACK)
                        }

                    }
                }

                override fun onError(e: Exception?) {
                }

            })

        holder.itemView.setOnClickListener { listener?.onClick(deezerAlbum) }

    }


    class AlbumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val albumCover: ImageView = itemView.findViewById(R.id.item_album_cover)
        val albumTitle: TextView = itemView.findViewById(R.id.item_album_title)
        val albumBackCover: LinearLayout = itemView.findViewById(R.id.item_background)
    }


    interface ClickListener {
        fun onClick(photo: DeezerAlbum)
    }
}
