package dev.aungkyawpaing.loki.adapter

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import dev.aungkyawpaing.loki.model.Element
import dev.aungkyawpaing.loki.model.metadata.ElementStyle
import dev.aungkyawpaing.loki.model.Column

class ColumnJsonAdapter(
    private val elementJsonAdapter: JsonAdapter<Element>,
    private val styleJsonAdapter: JsonAdapter<ElementStyle>,
) : JsonAdapter<Column>() {

    companion object {
        private const val KEY_TYPE = "type"
        private const val KEY_CHILDREN = "children"
        private const val KEY_STYLE = "style"
        private val KEY_OPTIONS = JsonReader.Options.of(
            KEY_CHILDREN,
            KEY_STYLE
        )
    }

    override fun fromJson(reader: JsonReader): Column {
        var children: MutableList<Element>? = null
        var style: ElementStyle? = null
        reader.beginObject()

        while (reader.hasNext()) {

            when (reader.selectName(KEY_OPTIONS)) {
                0 -> {
                    if (children == null) children = mutableListOf()
                    reader.beginArray()
                    while (reader.hasNext()) {
                        val element = elementJsonAdapter.fromJson(reader) ?: break
                        children.add(element)

                    }
                    reader.endArray()
                }
                1 -> {
                    style = styleJsonAdapter.fromJson(reader)
                }
                else -> {
                    reader.skipName()
                    reader.skipValue()
                }
            }
        }

        reader.endObject()

        if (children == null) {
            throw JsonDataException("Required property children is missing")
        }

        return Column(
            children = children.toList(),
            style = style
        )
    }

    override fun toJson(writer: JsonWriter, value: Column?) {
        if (value == null) {
            writer.nullValue()
        } else {
            writer.beginObject()

            writer.name(KEY_TYPE)
            writer.value(value.type.typeString)

            writer.name(KEY_CHILDREN)
            writer.beginArray()
            value.children.forEach { element ->
                elementJsonAdapter.toJson(writer, element)
            }
            writer.endArray()

            writer.name(KEY_STYLE)
            styleJsonAdapter.toJson(writer, value.style)

            writer.endObject()
        }
    }
}