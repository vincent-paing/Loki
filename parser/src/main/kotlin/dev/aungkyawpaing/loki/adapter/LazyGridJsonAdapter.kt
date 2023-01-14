package dev.aungkyawpaing.loki.adapter

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import dev.aungkyawpaing.loki.model.LazyElement
import dev.aungkyawpaing.loki.model.LazyGrid
import dev.aungkyawpaing.loki.model.metadata.ElementStyle
import dev.aungkyawpaing.loki.model.metadata.Orientation

class LazyGridJsonAdapter(
    private val orientationJsonAdapter: JsonAdapter<Orientation>,
    private val lazyElementJsonAdapter: JsonAdapter<LazyElement>,
    private val styleJsonAdapter: JsonAdapter<ElementStyle>,
) : JsonAdapter<LazyGrid>() {

    companion object {
        private const val KEY_TYPE = "type"
        private const val KEY_ORIENTATION = "orientation"
        private const val KEY_NUM_OF_ROWS_COLUMN = "numOfRowColumn"
        private const val KEY_CHILDREN = "children"
        private const val KEY_STYLE = "style"
        private val OPTIONS = JsonReader.Options.of(KEY_ORIENTATION, KEY_NUM_OF_ROWS_COLUMN, KEY_CHILDREN, KEY_STYLE)
    }

    override fun fromJson(reader: JsonReader): LazyGrid {
        var orientation: Orientation? = null
        var numOfRowColumn: Int? = null
        val children: MutableList<LazyElement> = mutableListOf()
        var style: ElementStyle? = null

        reader.beginObject()
        while (reader.hasNext()) {
            when (reader.selectName(OPTIONS)) {
                0 -> {
                    orientation = orientationJsonAdapter.fromJson(reader)
                }
                1 -> {
                    numOfRowColumn = reader.nextInt()
                }
                2 -> {
                    reader.beginArray()
                    while (reader.hasNext()) {
                        val element = lazyElementJsonAdapter.fromJson(reader) ?: break
                        children.add(element)
                    }
                    reader.endArray()
                }
                3 -> {
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

        if (numOfRowColumn == null) {
            throw IllegalArgumentException("Required property 'numOfRowColumn' is missing")
        }

        return LazyGrid(
            orientation = orientation,
            numOfRowColumn = numOfRowColumn,
            children = children,
            style = style
        )
    }

    override fun toJson(writer: JsonWriter, value: LazyGrid?) {
        writer.beginObject()

        writer.name(KEY_TYPE)
        writer.value(value!!.type.typeString)

        writer.name(KEY_ORIENTATION)
        orientationJsonAdapter.toJson(writer, value.orientation)

        writer.name(KEY_NUM_OF_ROWS_COLUMN)
        writer.value(value.numOfRowColumn)

        writer.name(KEY_CHILDREN)
        writer.beginArray()
        value.children.forEach { lazyElement ->
            lazyElementJsonAdapter.toJson(writer, lazyElement)
        }
        writer.endArray()

        writer.endObject()
    }
}