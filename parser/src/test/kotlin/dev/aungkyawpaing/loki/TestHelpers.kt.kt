package dev.aungkyawpaing.loki

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

inline fun <reified T> getJsonAdapter(): JsonAdapter<T> {
    return Moshi.Builder().add(LokiJsonAdapterFactory()).build().adapter(T::class.java)
}