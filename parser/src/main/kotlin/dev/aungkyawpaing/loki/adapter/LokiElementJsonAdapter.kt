package dev.aungkyawpaing.loki.adapter

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import dev.aungkyawpaing.loki.model.LokiElement
import dev.aungkyawpaing.loki.model.LokiElementType
import dev.aungkyawpaing.loki.model.Text

class LokiElementJsonAdapter(
    private val textJsonAdapter: JsonAdapter<Text>,
) : JsonAdapter<LokiElement>() {

    companion object {
        private const val KEY_TYPE = "type"
    }

    override fun fromJson(reader: JsonReader): LokiElement? {
        var element: LokiElement? = null

        while (reader.hasNext()) {
            val jsonValueMap = reader.readJsonValue() as Map<*, *>
            val typeString = jsonValueMap[KEY_TYPE] as String

            when (LokiElementType.fromTypeString(typeString)) {
                LokiElementType.TEXT -> {
                    element = textJsonAdapter.fromJsonValue(jsonValueMap)
                }
                else -> {
                    throw IllegalArgumentException("Illegal type: $typeString found. Refer to Loki spec")
                }
            }
        }

        return element
    }

    override fun toJson(writer: JsonWriter, value: LokiElement?) {
        if (value == null) {
            writer.nullValue()
        } else {
            when (value.type) {
                LokiElementType.TEXT -> {
                    textJsonAdapter.toJson(writer, value as Text)
                }
            }
        }
    }
}