package com.example.lays.deezingmusic.track

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.Group
import android.widget.TextView
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lays.deezingmusic.IMain
import com.example.lays.deezingmusic.MainActivity
import com.example.lays.deezingmusic.R
import com.example.lays.deezingmusic.model.DeezerTrack
import com.example.lays.deezingmusic.services.PlayerService
import kotlinx.android.synthetic.main.activity_main.*
import com.example.lays.deezingmusic.model.DeezerAlbum
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import org.w3c.dom.Text


class TrackFragment: Fragment() {

    private lateinit var trackRecyclerView: RecyclerView
    private var trackAdapter: TrackAdapter? = null
    private lateinit var trackViewModel: TrackViewModel
    private val args by navArgs<TrackFragmentArgs>()
    private lateinit var playerService: PlayerService
    private lateinit var iMain: IMain
    private lateinit var motionLayout: MotionLayout

    private lateinit var backgroundTrackAlbum: View
    private lateinit var imgTrackAlbum: ImageView
    private lateinit var titleTrackAlbum: TextView

    var isAlreadyPlayedOneTimes: Boolean = false
    private var pause:Boolean = false

    val gson = Gson()

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        playerService = PlayerService()
        try {
            iMain = activity as IMain
            // listener.showFormula(show?);
        } catch (castException: ClassCastException) {
            /** The activity does not implement the listener.  */
        }


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.track_fragment, null)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        trackRecyclerView = view.findViewById(R.id.track_recyclerview)
        backgroundTrackAlbum = view.findViewById(R.id.backgroundTrackAlbum)
        imgTrackAlbum = view.findViewById(R.id.imgTrackAlbum)
        titleTrackAlbum = view.findViewById(R.id.titleTrackAlbum)

        val albumObj = gson.fromJson(args.albumData, DeezerAlbum::class.java)

        with(albumObj) {

            Picasso.get()
                .load(albumObj.coverMedium)
                .placeholder(R.drawable.ic_album_foreground)
                .into(imgTrackAlbum, object : com.squareup.picasso.Callback {
                    override fun onSuccess() {
                        val bmpDrawable = imgTrackAlbum.drawable as BitmapDrawable
                        Palette.from(bmpDrawable.bitmap).generate {
                            it?.let { palette ->
                                val color = palette.getLightMutedColor(Color.argb(8, 200, 55, 155))
                                backgroundTrackAlbum.setBackgroundColor(color)
                                titleTrackAlbum.setTextColor(Color.BLACK)
                            }

                        }
                    }

                    override fun onError(e: Exception?) {
                    }

                })
        }

        titleTrackAlbum.text = albumObj.title
        initRecyclerView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        trackViewModel = ViewModelProviders.of(this).get(TrackViewModel::class.java)

        trackViewModel.trackLiveData.observe(viewLifecycleOwner, Observer {
            updateData(it)
        })

        val albumConvertGsonToObj = gson.fromJson(args.albumData, DeezerAlbum::class.java)


        //trackViewModel.getDeezerTracks(args.trackId)
        trackViewModel.getDeezerTracks(albumConvertGsonToObj.id)
    }

    private fun initRecyclerView() {
        trackAdapter = TrackAdapter()
        trackAdapter?.setListener(object : TrackAdapter.ClickListener {
            override fun onClick(music: DeezerTrack) {
                Log.d("initRecyclerView", music.toString())

                trackViewModel.trackLiveData.observe(viewLifecycleOwner, Observer {
                    if(!isAlreadyPlayedOneTimes) {
                        isAlreadyPlayedOneTimes = true
                        iMain.showPlayerBar()
                    }
                    if(it.size != 0) {
                        iMain.initPlayer(it, it.indexOf(music))
                        playerService.initPlayer(it, it.indexOf(music))
                    }
                })

            }

        })

        trackRecyclerView.adapter = trackAdapter
        trackRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    fun updateData(data: List<DeezerTrack>) {
        trackAdapter?.setData(data)
    }
}