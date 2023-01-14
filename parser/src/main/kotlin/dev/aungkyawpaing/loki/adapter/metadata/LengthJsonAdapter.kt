package dev.aungkyawpaing.loki.adapter.metadata

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import dev.aungkyawpaing.loki.model.metadata.Length
import java.lang.IllegalArgumentException
import kotlin.math.roundToInt

class LengthJsonAdapter : JsonAdapter<Length>() {
    override fun fromJson(reader: JsonReader): Length? {
        if (reader.peek() == JsonReader.Token.NULL) {
            reader.skipValue()
            return null
        }

        val value = reader.nextString()

        if (value.toDoubleOrNull() != null) {
            return Length.Number(value.toDoubleOrNull()!!.roundToInt())
        }

        if (value == "max") {
            return Length.Max
        }

        throw IllegalArgumentException("Unknown length : ${value}. It must either be numeral or 'max'")
    }

    override fun toJson(writer: JsonWriter, value: Length?) {
        when (value) {
            Length.Max -> {
                writer.value("max")
            }
            is Length.Number -> {
                writer.value(value.value)
            }
            null -> writer.nullValue()
        }
    }
}