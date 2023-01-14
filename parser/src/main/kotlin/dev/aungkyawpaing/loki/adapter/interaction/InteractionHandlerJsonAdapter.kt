package dev.aungkyawpaing.loki.adapter.interaction

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import dev.aungkyawpaing.loki.model.interaction.InteractionHandler
import dev.aungkyawpaing.loki.model.interaction.InteractionHandlerType
import dev.aungkyawpaing.loki.model.interaction.NavigationInteractionHandler

class InteractionHandlerJsonAdapter(
    private val navigationInteractionHandlerJsonAdapter: JsonAdapter<NavigationInteractionHandler>
) : JsonAdapter<InteractionHandler>() {

    companion object {
        private const val KEY_TYPE = "type"
    }

    override fun fromJson(reader: JsonReader): InteractionHandler? {
        var interactionHandler: InteractionHandler? = null

        while (reader.hasNext()) {
            val jsonValueMap = reader.readJsonValue() as Map<*, *>
            val typeString = jsonValueMap[KEY_TYPE] as String

            when (InteractionHandlerType.fromTypeString(typeString)) {
                InteractionHandlerType.NAVIGATION -> {
                    interactionHandler = navigationInteractionHandlerJsonAdapter.fromJsonValue(jsonValueMap)
                }
                null -> {
                    throw IllegalArgumentException("Illegal type: $typeString found. Refer to Loki spec")
                }
            }
        }

        return interactionHandler
    }

    override fun toJson(writer: JsonWriter, value: InteractionHandler?) {
        if (value == null) {
            writer.nullValue()
        } else {
            when (value.type) {
                InteractionHandlerType.NAVIGATION -> {
                    navigationInteractionHandlerJsonAdapter.toJson(writer, value as NavigationInteractionHandler)
                }
            }
        }
    }
}