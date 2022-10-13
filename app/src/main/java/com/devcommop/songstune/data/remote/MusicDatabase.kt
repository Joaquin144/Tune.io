package com.devcommop.songstune.data.remote

import com.devcommop.songstune.common.Constants
import com.devcommop.songstune.data.entities.Song
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class MusicDatabase {
    private val firestore = FirebaseFirestore.getInstance()
    private val songCollection = firestore.collection(Constants.SONG_COLLECTION)

    suspend fun getAllSongs(): List<Song> {
        return try{
            //songCollection.get().await() ---> this will return us an single object of type Unit [check Any ?]
            songCollection.get().await().toObjects(Song::class.java)
        }catch(exception: Exception){
            emptyList()
        }
    }
}