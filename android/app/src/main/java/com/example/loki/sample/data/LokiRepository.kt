package com.example.loki.sample.data

import kotlinx.coroutines.flow.Flow

interface LokiRepository {

  fun getMainPageJson(): Flow<String>

  fun getDetailPageJson(): Flow<String>

}