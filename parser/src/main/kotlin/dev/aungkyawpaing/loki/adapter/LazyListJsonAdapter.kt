package dev.aungkyawpaing.loki.adapter

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import dev.aungkyawpaing.loki.model.LazyElement
import dev.aungkyawpaing.loki.model.LazyList
import dev.aungkyawpaing.loki.model.metadata.ElementStyle
import dev.aungkyawpaing.loki.model.metadata.Orientation
import java.lang.IllegalArgumentException

class LazyListJsonAdapter(
    private val orientationJsonAdapter: JsonAdapter<Orientation>,
    private val lazyElementJsonAdapter: JsonAdapter<LazyElement>,
    private val styleJsonAdapter: JsonAdapter<ElementStyle>,
) : JsonAdapter<LazyList>() {

    companion object {
        private const val KEY_TYPE = "type"
        private const val KEY_ORIENTATION = "orientation"
        private const val KEY_CHILDREN = "children"
        private const val KEY_STYLE = "style"
        private val OPTIONS = JsonReader.Options.of(KEY_ORIENTATION, KEY_CHILDREN, KEY_STYLE)
    }

    override fun fromJson(reader: JsonReader): LazyList {
        var orientation: Orientation? = null
        val children: MutableList<LazyElement> = mutableListOf()
        var style: ElementStyle? = null

        reader.beginObject()
        while (reader.hasNext()) {
            when (reader.selectName(OPTIONS)) {
                0 -> {
                    orientation = orientationJsonAdapter.fromJson(reader)
                }
                1 -> {
                    reader.beginArray()
                    while (reader.hasNext()) {
                        val element = lazyElementJsonAdapter.fromJson(reader) ?: break
                        children.add(element)
                    }
                    reader.endArray()
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

        if (orientation == null) {
            throw IllegalArgumentException("Required property 'orientation' is missing")
        }

        return LazyList(
            orientation = orientation,
            children = children,
            style = style
        )
    }

    override fun toJson(writer: JsonWriter, value: LazyList?) {
        writer.beginObject()

        writer.name(KEY_TYPE)
        writer.value(value!!.type.typeString)

        writer.name(KEY_ORIENTATION)
        orientationJsonAdapter.toJson(writer, value.orientation)

        writer.name(KEY_CHILDREN)
        writer.beginArray()
        value.children.forEach { lazyElement ->
            lazyElementJsonAdapter.toJson(writer, lazyElement)
        }
        writer.endArray()

        writer.endObject()
    }
}