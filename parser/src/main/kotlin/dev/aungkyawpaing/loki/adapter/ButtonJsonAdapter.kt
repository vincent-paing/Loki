package dev.aungkyawpaing.loki.adapter

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import dev.aungkyawpaing.loki.model.Button
import dev.aungkyawpaing.loki.model.ButtonStyle
import dev.aungkyawpaing.loki.model.metadata.ElementStyle
import dev.aungkyawpaing.loki.model.metadata.TextStyle

class ButtonJsonAdapter(
    private val buttonStyleJsonAdapter: JsonAdapter<ButtonStyle>,
    private val textStyleJsonAdapter: JsonAdapter<TextStyle>,
    private val styleJsonAdapter: JsonAdapter<ElementStyle>
) : JsonAdapter<Button>() {

    companion object {
        private const val KEY_TYPE = "type"
        private const val KEY_TEXT = "text"
        private const val KEY_TEXT_STYLE = "textStyle"
        private const val KEY_BUTTON_STYLE = "buttonStyle"
        private const val KEY_COLOR = "color"
        private const val KEY_STYLE = "style"

        private val KEY_OPTIONS = JsonReader.Options.of(KEY_TEXT, KEY_TEXT_STYLE, KEY_BUTTON_STYLE, KEY_COLOR, KEY_STYLE)
    }

    override fun fromJson(reader: JsonReader): Button {
        var text: String? = null
        var textStyle: TextStyle? = null
        var buttonStyle: ButtonStyle? = null
        var color: String? = null
        var style: ElementStyle? = null

        reader.beginObject()
        while (reader.hasNext()) {
            when (reader.selectName(KEY_OPTIONS)) {
                0 -> {
                    text = reader.nextString()
                }
                1 -> {
                    textStyle = textStyleJsonAdapter.fromJson(reader)
                }
                2 -> {
                    buttonStyle = buttonStyleJsonAdapter.fromJson(reader)
                }
                3 -> {
                    color = reader.nextString()
                }
                4 -> {
                    style = styleJsonAdapter.fromJson(reader)
                }
                else -> {
                    reader.skipName()
                    reader.skipValue()
                }
            }
        }
        reader.endObject()

        if (text == null) {
            throw IllegalArgumentException("Required property text is missing")
        }

        if (textStyle == null) {
            throw IllegalArgumentException("Required property textStyle is missing")
        }

        if (buttonStyle == null) {
            throw IllegalArgumentException("Required property buttonStyle is missing")
        }

        if (color == null) {
            throw IllegalArgumentException("Required property color is missing")
        }

        return Button(
            text = text,
            textStyle = textStyle,
            buttonStyle = buttonStyle,
            color = color,
            style = style
        )
    }

    override fun toJson(writer: JsonWriter, value: Button?) {
        if (value == null) {
            writer.nullValue()
        } else {
            writer.beginObject() // {

            writer.name(KEY_TYPE)
            writer.value(value.type.typeString)

            writer.name(KEY_TEXT)
            writer.value(value.text)

            writer.name(KEY_TEXT_STYLE)
            textStyleJsonAdapter.toJson(writer, value.textStyle)

            writer.name(KEY_BUTTON_STYLE)
            buttonStyleJsonAdapter.toJson(writer, value.buttonStyle)

            writer.name(KEY_COLOR)
            writer.value(value.color)

            writer.endObject() // }
        }
    }


}