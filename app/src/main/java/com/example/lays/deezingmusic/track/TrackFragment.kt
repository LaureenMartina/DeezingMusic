package com.example.lays.deezingmusic.track

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lays.deezingmusic.R
import com.example.lays.deezingmusic.album.AlbumAdapter
import com.example.lays.deezingmusic.album.AlbumViewModel
import com.example.lays.deezingmusic.model.DeezerAlbum
import com.example.lays.deezingmusic.model.DeezerTrack

class TrackFragment: Fragment() {

    private lateinit var trackRecyclerView: RecyclerView
    private var trackAdapter: TrackAdapter? = null
    private lateinit var trackViewModel: TrackViewModel
    private val args by navArgs<TrackFragmentArgs>()

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
            }

        })

        trackRecyclerView.adapter = trackAdapter
        trackRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    fun updateData(data: List<DeezerTrack>) {
        trackAdapter?.setData(data)
    }
}