package dev.aungkyawpaing.loki.adapter

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import dev.aungkyawpaing.loki.model.Card
import dev.aungkyawpaing.loki.model.Element
import dev.aungkyawpaing.loki.model.metadata.ElementStyle

class CardJsonAdapter(
    private val elementJsonAdapter: JsonAdapter<Element>,
    private val styleJsonAdapter: JsonAdapter<ElementStyle>,
) : JsonAdapter<Card>() {

    companion object {
        private const val KEY_TYPE = "type"
        private const val KEY_ROUND_CORNER_RADIUS = "cornerRadius"
        private const val KEY_CHILD = "child"
        private const val KEY_STYLE = "style"
        private val KEY_OPTIONS = JsonReader.Options.of(
            KEY_CHILD,
            KEY_ROUND_CORNER_RADIUS,
            KEY_STYLE
        )
    }

    override fun fromJson(reader: JsonReader): Card {
        var child: Element? = null
        var cornerRadius = 0
        var style: ElementStyle? = null
        reader.beginObject()

        while (reader.hasNext()) {

            when (reader.selectName(KEY_OPTIONS)) {
                0 -> {
                    child = elementJsonAdapter.fromJson(reader) ?: break
                }
                1 -> {
                    cornerRadius = reader.nextInt()
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

        if (child == null) {
            throw IllegalArgumentException("Required property child is missing")
        }

        return Card(
            cornerRadius = cornerRadius,
            child = child,
            style = style
        )
    }

    override fun toJson(writer: JsonWriter, value: Card?) {
        if (value == null) {
            writer.nullValue()
        } else {
            writer.beginObject()

            writer.name(KEY_TYPE)
            writer.value(value.type.typeString)

            writer.name(KEY_ROUND_CORNER_RADIUS)
            writer.value(value.cornerRadius)

            writer.name(KEY_CHILD)
            elementJsonAdapter.toJson(writer, value.child)

            writer.name(KEY_STYLE)
            styleJsonAdapter.toJson(writer, value.style)

            writer.endObject()
        }
    }
}