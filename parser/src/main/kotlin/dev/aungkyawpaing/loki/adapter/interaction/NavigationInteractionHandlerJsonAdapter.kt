package dev.aungkyawpaing.loki.adapter.interaction

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import dev.aungkyawpaing.loki.model.interaction.NavigationInteractionHandler

class NavigationInteractionHandlerJsonAdapter : JsonAdapter<NavigationInteractionHandler>() {

    companion object {
        private const val KEY_TYPE = "type"
        private const val KEY_PAGE = "page"
        private val KEY_OPTIONS = JsonReader.Options.of(KEY_PAGE)
    }

    override fun fromJson(reader: JsonReader): NavigationInteractionHandler {
        var page: String? = null

        reader.beginObject()

        while (reader.hasNext()) {
            when (reader.selectName(KEY_OPTIONS)) {
                0 -> {
                    page = reader.nextString()
                }
                else -> {
                    reader.skipName()
                    reader.skipValue()
                }
            }
        }

        reader.endObject()

        if (page == null) {
            throw IllegalArgumentException("Required property 'page' is missing")
        }

        return NavigationInteractionHandler(page = page)
    }

    override fun toJson(writer: JsonWriter, value: NavigationInteractionHandler?) {
        writer.beginObject()

        writer.name(KEY_TYPE)
        writer.value(value!!.type.typeString)

        writer.name(KEY_PAGE)
        writer.value(value.page)

        writer.endObject()
    }
}