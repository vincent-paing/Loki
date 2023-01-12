package dev.aungkyawpaing.loki.adapter

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import dev.aungkyawpaing.loki.model.LokiElementType
import dev.aungkyawpaing.loki.model.Text
import dev.aungkyawpaing.loki.model.metadata.TextStyle

class TextJsonAdapter constructor(
    private val textStyleJsonAdapter: JsonAdapter<TextStyle>
) : JsonAdapter<Text>() {

    companion object {
        private const val KEY_TYPE = "type"
        private const val KEY_TEXT = "text"
        private const val KEY_TEXT_STYLE = "textStyle"
        private val KEY_OPTIONS = JsonReader.Options.of(KEY_TEXT, KEY_TEXT_STYLE)
    }

    override fun fromJson(reader: JsonReader): Text {
        var text: String? = null
        var textStyle: TextStyle? = null

        reader.beginObject()
        while (reader.hasNext()) {
            when (reader.selectName(KEY_OPTIONS)) {
                0 -> {
                    text = reader.nextString()
                }
                1 -> {
                    textStyle = textStyleJsonAdapter.fromJson(reader)
                }
                else -> {
                    reader.skipName()
                    reader.skipValue()
                }
            }
        }

        reader.endObject()

        if (text == null) {
            throw JsonDataException("Required property text is missing")
        }

        if (textStyle == null) {
            throw JsonDataException("Required property textStyle is missing")
        }

        return Text(
            text = text,
            textStyle = textStyle
        )
    }

    override fun toJson(writer: JsonWriter, value: Text?) {
        if (value == null) {
            writer.nullValue()
        } else {
            writer.beginObject() // {
            writer.name(KEY_TYPE) // "type":
            writer.value(LokiElementType.TEXT.typeString) // "text",

            writer.name(KEY_TEXT) // "text":
            writer.value(value.text)

            writer.name(KEY_TEXT_STYLE) // "textStyle":
            textStyleJsonAdapter.toJson(writer, value.textStyle)

            writer.endObject() // }
        }
    }


}