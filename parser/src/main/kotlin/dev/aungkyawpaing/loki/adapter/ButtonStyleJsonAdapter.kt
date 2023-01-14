package dev.aungkyawpaing.loki.adapter

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import dev.aungkyawpaing.loki.model.ButtonStyle
import java.lang.IllegalArgumentException

class ButtonStyleJsonAdapter : JsonAdapter<ButtonStyle>() {
    override fun fromJson(reader: JsonReader): ButtonStyle {

        return when (reader.nextString()) {
            "filled" -> ButtonStyle.FILLED
            "outlined" -> ButtonStyle.OUTLINED
            "text" -> ButtonStyle.TEXT
            else -> throw IllegalArgumentException("Unknown value : random-value. It must either be 'filled','outlined' or 'text'")
        }
    }

    override fun toJson(writer: JsonWriter, value: ButtonStyle?) {
       when(value) {
           ButtonStyle.FILLED -> {
               writer.value("filled")
           }
           ButtonStyle.OUTLINED -> {
               writer.value("outlined")
           }
           ButtonStyle.TEXT -> {
               writer.value("text")
           }
           null -> throw IllegalStateException("ButtonStyle can never be null")
       }
    }
}