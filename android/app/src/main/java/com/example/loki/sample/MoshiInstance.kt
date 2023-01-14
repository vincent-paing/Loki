package com.example.loki.sample

import com.squareup.moshi.Moshi
import dev.aungkyawpaing.loki.LokiJsonAdapterFactory

object MoshiInstance {

  private var _moshi : Moshi? = null

  val moshi : Moshi get() {
    if (_moshi == null ) {
     _moshi = Moshi.Builder()
        .add(LokiJsonAdapterFactory())
        .build()
    }
    return _moshi!!
  }

}