package dev.aungkyawpaing.loki.adapter

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import dev.aungkyawpaing.loki.model.Image
import dev.aungkyawpaing.loki.model.metadata.ElementStyle

class ImageJsonAdapter(
    private val styleJsonAdapter: JsonAdapter<ElementStyle>
) : JsonAdapter<Image>() {

    companion object {
        private const val KEY_TYPE = "type"
        private const val KEY_URL = "url"
        private const val KEY_ALT_TEXT = "altText"
        private const val KEY_STYLE = "style"
        private val KEY_OPTIONS = JsonReader.Options.of(KEY_URL, KEY_ALT_TEXT, KEY_STYLE)
    }

    override fun fromJson(reader: JsonReader): Image {
        var url: String? = null
        var altText: String? = null
        var style: ElementStyle? = null

        reader.beginObject()
        while (reader.hasNext()) {
            when (reader.selectName(KEY_OPTIONS)) {
                0 -> {
                    url = reader.nextString()
                }
                1 -> {
                    altText = reader.nextString()
                }
                2 -> {
                    style = styleJsonAdapter.fromJson(reader)
                }
                else -> {
                    reader.skipName()
                    reader.skipValue()
                }
            }
        }

        reader.endObject()

        if (url == null) {
            throw JsonDataException("Required property url is missing")
        }

        return Image(
            url = url,
            altText = altText,
            style = style
        )
    }

    override fun toJson(writer: JsonWriter, value: Image?) {

        if (value == null) {
            writer.nullValue()
            return
        }
        writer.beginObject()

        writer.name(KEY_TYPE)
        writer.value(value.type.typeString)

        writer.name(KEY_URL)
        writer.value(value.url)

        writer.name(KEY_ALT_TEXT)
        writer.value(value.altText)

        writer.name(KEY_STYLE)
        styleJsonAdapter.toJson(writer, value.style)

        writer.endObject()
    }
}