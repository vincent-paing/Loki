package dev.aungkyawpaing.loki.adapter

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import dev.aungkyawpaing.loki.model.Element
import dev.aungkyawpaing.loki.model.LazyElement
import java.lang.IllegalArgumentException

class LazyElementJsonAdapter(
    private val elementJsonAdapter: JsonAdapter<Element>
) : JsonAdapter<LazyElement>() {

    companion object {
        private const val KEY_ID = "id"
        private const val KEY_ELEMENT = "element"
        private val KEY_OPTIONS = JsonReader.Options.of(KEY_ID, KEY_ELEMENT)
    }

    override fun fromJson(reader: JsonReader): LazyElement {
        var id: String? = null
        var element: Element? = null

        reader.beginObject()
        while (reader.hasNext()) {
            when (reader.selectName(KEY_OPTIONS)) {
                0 -> {
                    id = reader.nextString()
                }
                1 -> {
                    element = elementJsonAdapter.fromJson(reader)
                }
                else -> {
                    reader.skipName()
                    reader.skipValue()
                }
            }
        }
        reader.endObject()

        if (id == null) {
            throw IllegalArgumentException("Required property 'id' is missing")
        }

        if (element == null) {
            throw IllegalArgumentException("Required property 'element' is missing")
        }

        return LazyElement(
            id = id,
            element = element
        )
    }

    override fun toJson(writer: JsonWriter, value: LazyElement?) {
        writer.beginObject()

        writer.name(KEY_ID)
        writer.value(value!!.id)

        writer.name(KEY_ELEMENT)
        elementJsonAdapter.toJson(writer, value.element)

        writer.endObject()
    }
}