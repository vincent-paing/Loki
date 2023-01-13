package dev.aungkyawpaing.loki

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import dev.aungkyawpaing.loki.adapter.*
import dev.aungkyawpaing.loki.adapter.metadata.ElementStyleJsonAdapter
import dev.aungkyawpaing.loki.adapter.metadata.PaddingJsonAdapter
import dev.aungkyawpaing.loki.adapter.metadata.TextStyleJsonAdapter
import dev.aungkyawpaing.loki.adapter.metadata.LengthJsonAdapter
import dev.aungkyawpaing.loki.model.*
import dev.aungkyawpaing.loki.model.metadata.*
import java.lang.reflect.Type

class LokiJsonAdapterFactory : JsonAdapter.Factory {

    override fun create(type: Type, annotations: MutableSet<out Annotation>, moshi: Moshi): JsonAdapter<*>? {
        return when (type) {
            Element::class.java -> {
                ElementJsonAdapter(
                    moshi.adapter(Text::class.java),
                    moshi.adapter(Image::class.java),
                    moshi.adapter(Row::class.java),
                    moshi.adapter(Column::class.java)
                )
            }
            Text::class.java -> {
                TextJsonAdapter(
                    moshi.adapter(TextStyle::class.java), moshi.adapter(ElementStyle::class.java)
                )
            }
            TextStyle::class.java -> {
                TextStyleJsonAdapter()
            }
            Image::class.java -> {
                ImageJsonAdapter(
                    moshi.adapter(ElementStyle::class.java)
                )
            }
            Row::class.java -> {
                RowJsonAdapter(
                    moshi.adapter(Element::class.java),
                    moshi.adapter(ElementStyle::class.java)
                )
            }
            Column::class.java -> {
                ColumnJsonAdapter(
                    moshi.adapter(Element::class.java),
                    moshi.adapter(ElementStyle::class.java)
                )
            }
            Padding::class.java -> {
                PaddingJsonAdapter()
            }
            Length::class.java -> {
                LengthJsonAdapter()
            }
            ElementStyle::class.java -> {
                ElementStyleJsonAdapter(
                    moshi.adapter(Length::class.java), moshi.adapter(Padding::class.java)
                )
            }
            else -> {
                return null
            }
        }

    }
}