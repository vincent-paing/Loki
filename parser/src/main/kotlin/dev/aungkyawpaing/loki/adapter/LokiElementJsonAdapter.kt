package dev.aungkyawpaing.loki.adapter

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import dev.aungkyawpaing.loki.model.AbstractElement
import dev.aungkyawpaing.loki.model.LokiElementType
import dev.aungkyawpaing.loki.model.Text

class LokiElementJsonAdapter(
    private val textJsonAdapter: JsonAdapter<Text>,
) : JsonAdapter<AbstractElement>() {

    companion object {
        private const val KEY_TYPE = "type"
    }

    override fun fromJson(reader: JsonReader): AbstractElement? {
        var element: AbstractElement? = null

        while (reader.hasNext()) {
            val jsonValueMap = reader.readJsonValue() as Map<*, *>
            val typeString = jsonValueMap[KEY_TYPE] as String

            when (LokiElementType.fromTypeString(typeString)) {
                LokiElementType.TEXT -> {
                    element = textJsonAdapter.fromJsonValue(jsonValueMap)
                }
                LokiElementType.Element -> {

                }
                null -> {
                    throw IllegalArgumentException("Illegal type: $typeString found. Refer to Loki spec")
                }
            }
        }

        return element
    }

    override fun toJson(writer: JsonWriter, value: AbstractElement?) {
        if (value == null) {
            writer.nullValue()
        } else {
            when (value.type) {
                LokiElementType.TEXT -> {
                    textJsonAdapter.toJson(writer, value as Text)
                }
                LokiElementType.Element -> TODO()
            }
        }
    }
}