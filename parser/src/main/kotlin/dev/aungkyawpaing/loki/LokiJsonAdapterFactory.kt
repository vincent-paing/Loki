package dev.aungkyawpaing.loki

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import dev.aungkyawpaing.loki.adapter.LokiElementJsonAdapter
import dev.aungkyawpaing.loki.adapter.TextJsonAdapter
import dev.aungkyawpaing.loki.adapter.metadata.ElementStyleJsonAdapter
import dev.aungkyawpaing.loki.adapter.metadata.PaddingJsonAdapter
import dev.aungkyawpaing.loki.adapter.metadata.TextStyleJsonAdapter
import dev.aungkyawpaing.loki.adapter.metadata.LengthJsonAdapter
import dev.aungkyawpaing.loki.model.LokiElement
import dev.aungkyawpaing.loki.model.Text
import dev.aungkyawpaing.loki.model.metadata.ElementStyle
import dev.aungkyawpaing.loki.model.metadata.Padding
import dev.aungkyawpaing.loki.model.metadata.TextStyle
import dev.aungkyawpaing.loki.model.metadata.Length
import java.lang.reflect.Type

class LokiJsonAdapterFactory : JsonAdapter.Factory {

    override fun create(type: Type, annotations: MutableSet<out Annotation>, moshi: Moshi): JsonAdapter<*>? {
        return when (type) {
            LokiElement::class.java -> {
                LokiElementJsonAdapter(
                    moshi.adapter(Text::class.java)
                )
            }
            Text::class.java -> {
                TextJsonAdapter(moshi.adapter(TextStyle::class.java))
            }
            TextStyle::class.java -> {
                TextStyleJsonAdapter()
            }
            Padding::class.java -> {
                PaddingJsonAdapter()
            }
            Length::class.java -> {
                LengthJsonAdapter()
            }
            ElementStyle::class.java -> {
                ElementStyleJsonAdapter(
                    moshi.adapter(Length::class.java),
                    moshi.adapter(Padding::class.java)
                )
            }
            else -> {
                return null
            }
        }

    }
}