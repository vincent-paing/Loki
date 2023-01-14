package com.example.loki.sample.data

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.callbackFlow

class LokiRepositoryFirebaseImpl : LokiRepository {

  override fun getMainPageJson() = callbackFlow {

    val dataListener = object : ValueEventListener {
      override fun onDataChange(snapshot: DataSnapshot) {
        this@callbackFlow.trySendBlocking(snapshot.value as String)
      }

      override fun onCancelled(error: DatabaseError) {
        error.toException().printStackTrace()
      }
    }

    Firebase.database.getReference("main_page").addValueEventListener(dataListener)

    awaitClose {
      Firebase.database.getReference("main_page").removeEventListener(dataListener)
    }
  }

  override fun getDetailPageJson() = callbackFlow {

    val dataListener = object : ValueEventListener {
      override fun onDataChange(snapshot: DataSnapshot) {
        this@callbackFlow.trySendBlocking(snapshot.value as String)
      }

      override fun onCancelled(error: DatabaseError) {
        error.toException().printStackTrace()
      }
    }

    Firebase.database.getReference("detail_page").addValueEventListener(dataListener)

    awaitClose {
      Firebase.database.getReference("detail_page").removeEventListener(dataListener)
    }
  }

}