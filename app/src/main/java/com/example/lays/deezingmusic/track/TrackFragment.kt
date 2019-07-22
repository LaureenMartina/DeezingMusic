package com.example.lays.deezingmusic.track

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.Group
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lays.deezingmusic.IMain
import com.example.lays.deezingmusic.MainActivity
import com.example.lays.deezingmusic.R
import com.example.lays.deezingmusic.model.DeezerTrack
import com.example.lays.deezingmusic.services.PlayerService
import kotlinx.android.synthetic.main.activity_main.*


class TrackFragment: Fragment() {

    private lateinit var trackRecyclerView: RecyclerView
    private var trackAdapter: TrackAdapter? = null
    private lateinit var trackViewModel: TrackViewModel
    private val args by navArgs<TrackFragmentArgs>()
    private lateinit var playerService: PlayerService
    private lateinit var iMain: IMain

    var isAlreadyPlayedOneTimes: Boolean = false
    private var pause:Boolean = false


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
        initRecyclerView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        trackViewModel = ViewModelProviders.of(this).get(TrackViewModel::class.java)

        trackViewModel.trackLiveData.observe(viewLifecycleOwner, Observer {
            updateData(it)
        })

        trackViewModel.getDeezerTracks(args.trackId)
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