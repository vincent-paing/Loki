package dev.aungkyawpaing.loki.model.lazylist

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import dev.aungkyawpaing.loki.model.metadata.Orientation

class OrientationJsonAdapter : JsonAdapter<Orientation>() {

    override fun fromJson(reader: JsonReader): Orientation? {
        return when (reader.nextString()) {
            "horizontal" -> Orientation.HORIZONTAL
            "vertical" -> Orientation.VERTICAL
            else -> null
        }
    }

    override fun toJson(writer: JsonWriter, value: Orientation?) {
        when (value) {
            Orientation.HORIZONTAL -> {
                writer.value("horizontal")
            }
            Orientation.VERTICAL -> {
                writer.value("vertical")
            }
            null -> {
                throw IllegalStateException("Orientation cannot be null")
            }
        }
    }

}