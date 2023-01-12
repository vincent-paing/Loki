package dev.aungkyawpaing.loki

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

inline fun <reified T> getJsonAdapter(): JsonAdapter<T> {
    val geoshiJsonAdapterFactory = LokiJsonAdapterFactory()
    val moshi = Moshi.Builder().add(geoshiJsonAdapterFactory).build()
    return moshi.adapter(T::class.java)
}