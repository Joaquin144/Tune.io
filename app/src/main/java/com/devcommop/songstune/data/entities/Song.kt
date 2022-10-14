package com.devcommop.songstune.data.entities

data class Song(
    val mediaId: String = "",
    val title: String = "",
    val subtitle: String = "",//This will store the name of the artist i.e. singer
    val songUrl: String = "",//The url of this song in Firebase Storage
    val imageUrl: String = ""
)