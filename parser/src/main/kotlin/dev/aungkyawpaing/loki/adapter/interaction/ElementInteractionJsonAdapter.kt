package dev.aungkyawpaing.loki.adapter.interaction

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import dev.aungkyawpaing.loki.model.interaction.ElementInteractions
import dev.aungkyawpaing.loki.model.interaction.InteractionHandler

class ElementInteractionJsonAdapter(
    private val handlerJsonAdapter: JsonAdapter<InteractionHandler>
) : JsonAdapter<ElementInteractions>() {

    companion object {
        private const val KEY_ON_PRESS = "onPress"
        private val KEY_OPTIONS = JsonReader.Options.of(KEY_ON_PRESS)
    }

    override fun fromJson(reader: JsonReader): ElementInteractions {
        var onPress: InteractionHandler? = null

        reader.beginObject()

        while (reader.hasNext()) {
            when (reader.selectName(KEY_OPTIONS)) {
                0 -> {
                    onPress = handlerJsonAdapter.fromJson(reader)
                }
                else -> {
                    reader.skipValue()
                    reader.skipName()
                }
            }
        }

        reader.endObject()

        return ElementInteractions(onPress = onPress)
    }

    override fun toJson(writer: JsonWriter, value: ElementInteractions?) {
        writer.beginObject()

        if (value?.onPress != null) {
            writer.name(KEY_ON_PRESS)
            handlerJsonAdapter.toJson(writer, value.onPress)
        }

        writer.endObject()
    }

}