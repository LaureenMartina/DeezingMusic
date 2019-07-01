package com.example.lays.deezingmusic.dto

import com.google.gson.annotations.SerializedName

data class EAlbum(
        @SerializedName("artist")
        val artist: Artist,
        @SerializedName("cover")
        val cover: String,
        @SerializedName("cover_big")
        val coverBig: String,
        @SerializedName( "cover_medium")
        val coverMedium: String,
        @SerializedName("cover_small")
        val coverSmall: String,
        @SerializedName( "cover_xl")
        val coverXl: String,
        @SerializedName("duration")
        val duration: Int,
        @SerializedName("explicit_content_cover")
        val explicitContentCover: Int,
        @SerializedName("explicit_content_lyrics")
        val explicitContentLyrics: Int,
        @SerializedName("explicit_lyrics")
        val explicitLyrics: Boolean,
        @SerializedName("fans")
        val fans: Int,
        @SerializedName("genre_id")
        val genreId: Int,
        @SerializedName("genres")
        val genres: Genres,
        @SerializedName("id")
        val id: Int,
        @SerializedName("label")
        val label: String,
        @SerializedName("link")
        val link: String,
        @SerializedName("nb_tracks")
        val nbTracks: Int,
        @SerializedName("rating")
        val rating: Int,
        @SerializedName("release_date")
        val releaseDate: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("tracklist")
        val tracklist: String,
        @SerializedName("tracks")
        val tracks: Tracks,
        @SerializedName("type")
        val type: String
) {
    data class Genres(
            @SerializedName("data")
            val `data`: List<Data>
    ) {
        data class Data(
                @SerializedName("id")
                val id: Int,
                @SerializedName("name")
                val name: String,
                @SerializedName("picture")
                val picture: String,
                @SerializedName("type")
                val type: String
        )
    }
    data class Artist(
            @SerializedName("id")
            val id: Int,
            @SerializedName("name")
            val name: String,
            @SerializedName("picture")
            val picture: String,
            @SerializedName("picture_big")
            val pictureBig: String,
            @SerializedName("picture_medium")
            val pictureMedium: String,
            @SerializedName("picture_small")
            val pictureSmall: String,
            @SerializedName("picture_xl")
            val pictureXl: String,
            @SerializedName("tracklist")
            val tracklist: String,
            @SerializedName("type")
            val type: String
    )
    data class Tracks(
            @SerializedName("data")
            val `data`: List<Data>
    ) {
        data class Data(
                @SerializedName("artist")
                val artist: Artist,
                @SerializedName("duration")
                val duration: Int,
                @SerializedName("explicit_content_cover")
                val explicitContentCover: Int,
                @SerializedName("explicit_content_lyrics")
                val explicitContentLyrics: Int,
                @SerializedName("explicit_lyrics")
                val explicitLyrics: Boolean,
                @SerializedName("id")
                val id: Int,
                @SerializedName("link")
                val link: String,
                @SerializedName("preview")
                val preview: String,
                @SerializedName("rank")
                val rank: Int,
                @SerializedName("readable")
                val readable: Boolean,
                @SerializedName("title")
                val title: String,
                @SerializedName("title_short")
                val titleShort: String,
                @SerializedName("title_version")
                val titleVersion: String,
                @SerializedName("type")
                val type: String
        ) {
            data class Artist(
                    @SerializedName("id")
                    val id: Int,
                    @SerializedName("name")
                    val name: String,
                    @SerializedName("tracklist")
                    val tracklist: String,
                    @SerializedName("type")
                    val type: String
            )
        }
    }
}