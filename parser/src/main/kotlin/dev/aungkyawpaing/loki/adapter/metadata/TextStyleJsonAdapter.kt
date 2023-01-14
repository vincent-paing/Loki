package dev.aungkyawpaing.loki.adapter.metadata

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import dev.aungkyawpaing.loki.model.metadata.TextStyle
import java.io.IOException

class TextStyleJsonAdapter : JsonAdapter<TextStyle>() {

    companion object {
        private const val KEY_TEXT_SIZE = "textSize"
        private const val KEY_IS_BOLD = "isBold"
        private const val KEY_TEXT_COLOR = "textColor"
        private val KEY_OPTIONS = JsonReader.Options.of(KEY_TEXT_SIZE, KEY_IS_BOLD, KEY_TEXT_COLOR)
    }

    @Throws(IOException::class)
    override fun fromJson(reader: JsonReader): TextStyle {
        var textSize: Int? = null
        var isBold: Boolean? = null
        var textColor: String? = null

        reader.beginObject()
        while (reader.hasNext()) {
            when (reader.selectName(KEY_OPTIONS)) {
                0 -> {
                    textSize = reader.nextInt()
                }
                1 -> {
                    isBold = reader.nextBoolean()
                }
                2 -> {
                    textColor = reader.nextString()
                }
                else -> {
                    reader.skipName()
                    reader.skipValue()
                }
            }
        }

        reader.endObject()

        if (textSize == null) {
            throw IllegalArgumentException("Required property textStyle is missing")
        }

        return TextStyle(
            textSize = textSize,
            isBold = isBold,
            textColor = textColor
        )
    }

    @Throws(IOException::class)
    override fun toJson(writer: JsonWriter, value: TextStyle?) {
        if (value == null) {
            writer.nullValue()
        } else {
            writer.beginObject()
            writer.name(KEY_TEXT_SIZE)
            writer.value(value.textSize)

            writer.name(KEY_IS_BOLD)
            writer.value(value.isBold)

            writer.name(KEY_TEXT_COLOR)
            writer.value(value.textColor)

            writer.endObject()
        }
    }


}