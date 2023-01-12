package dev.aungkyawpaing.loki

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import dev.aungkyawpaing.loki.adapter.LokiElementJsonAdapter
import dev.aungkyawpaing.loki.adapter.TextJsonAdapter
import dev.aungkyawpaing.loki.adapter.TextStyleJsonAdapter
import dev.aungkyawpaing.loki.model.LokiElement
import dev.aungkyawpaing.loki.model.Text
import dev.aungkyawpaing.loki.model.TextStyle
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
            else -> {
                return null
            }
        }

    }
}