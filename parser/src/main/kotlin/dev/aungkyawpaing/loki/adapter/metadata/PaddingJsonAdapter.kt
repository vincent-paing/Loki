package dev.aungkyawpaing.loki.adapter.metadata

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import dev.aungkyawpaing.loki.model.metadata.Padding
import java.io.IOException

class PaddingJsonAdapter : JsonAdapter<Padding>() {
    companion object {
        private const val KEY_TOP = "top"
        private const val KEY_BOTTOM = "bottom"
        private const val KEY_LEFT = "left"
        private const val KEY_RIGHT = "right"
        private val KEY_OPTIONS = JsonReader.Options.of(KEY_TOP, KEY_BOTTOM, KEY_LEFT, KEY_RIGHT)
    }

    @Throws(IOException::class)
    override fun fromJson(reader: JsonReader): Padding? {

        if (reader.peek() == JsonReader.Token.NULL) {
            reader.skipValue()
            return null
        }

        var top = 0
        var bottom = 0
        var left = 0
        var right = 0

        reader.beginObject()
        while (reader.hasNext()) {
            when (reader.selectName(KEY_OPTIONS)) {
                0 -> {
                    top = reader.nextInt()
                }
                1 -> {
                    bottom = reader.nextInt()
                }
                2 -> {
                    left = reader.nextInt()
                }
                3 -> {
                    right = reader.nextInt()
                }
                else -> {
                    reader.skipName()
                    reader.skipValue()
                }
            }
        }

        reader.endObject()

        return Padding(
            top = top,
            bottom = bottom,
            left = left,
            right = right
        )
    }

    @Throws(IOException::class)
    override fun toJson(writer: JsonWriter, value: Padding?) {
        if (value == null) {
            writer.nullValue()
        } else {
            writer.beginObject()
            writer.name(KEY_TOP)
            writer.value(value.top)

            writer.name(KEY_BOTTOM)
            writer.value(value.bottom)

            writer.name(KEY_LEFT)
            writer.value(value.left)

            writer.name(KEY_RIGHT)
            writer.value(value.right)

            writer.endObject()
        }
    }
}