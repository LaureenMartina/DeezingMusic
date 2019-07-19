package com.example.lays.deezingmusic.album

import android.os.Bundle

import androidx.lifecycle.Observer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lays.deezingmusic.R
import com.example.lays.deezingmusic.model.DeezerAlbum

class AlbumFragment : Fragment() {

    private lateinit var albumRecyclerView: RecyclerView
    private var albumAdapter: AlbumAdapter? = null
    private lateinit var albumViewModel: AlbumViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.album_fragment, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        albumRecyclerView = view.findViewById(R.id.album_recyclerview)

        initRecyclerView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        albumViewModel = ViewModelProviders.of(this).get(AlbumViewModel::class.java)

        albumViewModel.albumLiveData.observe(viewLifecycleOwner, Observer {
            updateData(it)
        })

        albumViewModel.getDeezerAlbums()
    }

    private fun initRecyclerView() {
        albumAdapter = AlbumAdapter()
        albumAdapter?.setListener(object : AlbumAdapter.ClickListener {
            override fun onClick(album: DeezerAlbum) {
                val navAction = AlbumFragmentDirections.actionAlbumFragmentToTrackFragment(album.id)

                view?.also {
                    Navigation.findNavController(it).navigate(navAction)
                }
            }

        })

        albumRecyclerView.adapter = albumAdapter
        albumRecyclerView.layoutManager = GridLayoutManager(context,2)
    }

    fun updateData(data: List<DeezerAlbum>) {
        albumAdapter?.setData(data)
    }

}