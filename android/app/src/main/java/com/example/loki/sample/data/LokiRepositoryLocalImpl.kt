package com.example.loki.sample.data

import android.content.Context
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LokiRepositoryLocalImpl(
  context: Context
) : LokiRepository {
  private val assetManager = context.assets

  override fun getMainPageJson(): Flow<String> {
    val json = assetManager.open("data2.json").bufferedReader().use {
      it.readText()
    }
    return flow {
      emit(json)
    }
  }

  override fun getDetailPageJson(): Flow<String> {
    val json = assetManager.open("detail.json").bufferedReader().use {
      it.readText()
    }
    return flow {
      emit(json)
    }
  }

}
