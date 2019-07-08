package com.example.lays.deezingmusic.album

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        albumRecyclerView = view.findViewById(R.id.recyclerAlbums)

        initRecyclerView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        albumViewModel = ViewModelProviders.of(this).get(AlbumViewModel::class.java)

        albumViewModel.albumLiveData.observe(viewLifecycleOwner, observerAlbum)

        albumViewModel.getDeezerAlbums(nameArtist)
    }

    private fun initRecyclerView() {
        albumAdapter = AlbumAdapter()
        albumAdapter?.setListener(object : AlbumAdapter.ClickListener {
            override fun onClick(photo: DeezerAlbum) {

            }

        })

        albumRecyclerView.adapter = albumAdapter
        albumRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    fun updateData(data: List<DeezerAlbum>) {
        albumAdapter?.setData(data)
    }

}