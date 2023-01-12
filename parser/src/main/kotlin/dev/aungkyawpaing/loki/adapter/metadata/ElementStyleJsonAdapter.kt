package dev.aungkyawpaing.loki.adapter.metadata

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import dev.aungkyawpaing.loki.model.metadata.ElementStyle
import dev.aungkyawpaing.loki.model.metadata.Length
import dev.aungkyawpaing.loki.model.metadata.Padding

class ElementStyleJsonAdapter constructor(
    private val lengthJsonAdapter: JsonAdapter<Length>,
    private val paddingJsonAdapter: JsonAdapter<Padding>
) : JsonAdapter<ElementStyle>() {

    companion object {
        private const val KEY_WIDTH = "width"
        private const val KEY_HEIGHT = "height"
        private const val KEY_PADDING = "padding"
        private val KEY_OPTIONS = JsonReader.Options.of(KEY_WIDTH, KEY_HEIGHT, KEY_PADDING)
    }

    override fun fromJson(reader: JsonReader): ElementStyle? {
        var width: Length? = null
        var height: Length? = null
        var padding: Padding? = null

        if (reader.peek() == JsonReader.Token.NULL) {
            reader.skipValue()
            return null
        }

        reader.beginObject()
        while (reader.hasNext()) {
            when (reader.selectName(KEY_OPTIONS)) {
                0 -> {
                    width = lengthJsonAdapter.fromJson(reader)
                }
                1 -> {
                    height = lengthJsonAdapter.fromJson(reader)
                }
                2 -> {
                    padding = paddingJsonAdapter.fromJson(reader)
                }
                else -> {
                    reader.skipName()
                    reader.skipValue()
                }
            }
        }

        reader.endObject()

        return ElementStyle(width, height, padding)
    }

    override fun toJson(writer: JsonWriter, value: ElementStyle?) {
        if (value == null) {
            writer.nullValue()
            return
        }

        writer.beginObject()

        writer.name(KEY_WIDTH)
        lengthJsonAdapter.toJson(writer, value.width)

        writer.name(KEY_HEIGHT)
        lengthJsonAdapter.toJson(writer, value.height)

        writer.name(KEY_PADDING)
        paddingJsonAdapter.toJson(writer, value.padding)

        writer.endObject()
    }

}