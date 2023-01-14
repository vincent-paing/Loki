package dev.aungkyawpaing.loki

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import dev.aungkyawpaing.loki.adapter.*
import dev.aungkyawpaing.loki.adapter.metadata.*
import dev.aungkyawpaing.loki.model.*
import dev.aungkyawpaing.loki.adapter.LazyElementJsonAdapter
import dev.aungkyawpaing.loki.adapter.LazyListJsonAdapter
import dev.aungkyawpaing.loki.adapter.interaction.ElementInteractionJsonAdapter
import dev.aungkyawpaing.loki.adapter.interaction.InteractionHandlerJsonAdapter
import dev.aungkyawpaing.loki.adapter.interaction.NavigationInteractionHandlerJsonAdapter
import dev.aungkyawpaing.loki.adapter.metadata.OrientationJsonAdapter
import dev.aungkyawpaing.loki.model.interaction.ElementInteractions
import dev.aungkyawpaing.loki.model.interaction.InteractionHandler
import dev.aungkyawpaing.loki.model.interaction.NavigationInteractionHandler
import dev.aungkyawpaing.loki.model.metadata.*
import java.lang.reflect.Type

class LokiJsonAdapterFactory : JsonAdapter.Factory {

    override fun create(type: Type, annotations: MutableSet<out Annotation>, moshi: Moshi): JsonAdapter<*>? {
        return when (type) {
            Element::class.java -> {
                ElementJsonAdapter(
                    moshi.adapter(Button::class.java),
                    moshi.adapter(Text::class.java),
                    moshi.adapter(Image::class.java),
                    moshi.adapter(Row::class.java),
                    moshi.adapter(Column::class.java),
                    moshi.adapter(Card::class.java),
                    moshi.adapter(LazyList::class.java),
                    moshi.adapter(LazyGrid::class.java),
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
                    moshi.adapter(Element::class.java), moshi.adapter(ElementStyle::class.java)
                )
            }
            Column::class.java -> {
                ColumnJsonAdapter(
                    moshi.adapter(Element::class.java),
                    moshi.adapter(ElementStyle::class.java),
                    moshi.adapter(ElementInteractions::class.java)
                )
            }
            Card::class.java -> {
                CardJsonAdapter(
                    moshi.adapter(Element::class.java), moshi.adapter(ElementStyle::class.java)
                )
            }
            LazyList::class.java -> {
                LazyListJsonAdapter(
                    moshi.adapter(Orientation::class.java),
                    moshi.adapter(LazyElement::class.java),
                    moshi.adapter(ElementStyle::class.java),
                )
            }
            LazyGrid::class.java -> {
                LazyGridJsonAdapter(
                    moshi.adapter(Orientation::class.java),
                    moshi.adapter(LazyElement::class.java),
                    moshi.adapter(ElementStyle::class.java),
                )
            }
            LazyElement::class.java -> {
                LazyElementJsonAdapter(
                    moshi.adapter(Element::class.java)
                )
            }
            Orientation::class.java -> {
                OrientationJsonAdapter()
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
            Button::class.java -> {
                ButtonJsonAdapter(
                    moshi.adapter(ButtonStyle::class.java),
                    moshi.adapter(TextStyle::class.java),
                    moshi.adapter(ElementStyle::class.java)
                )
            }
            ButtonStyle::class.java -> {
                ButtonStyleJsonAdapter()
            }
            ElementInteractions::class.java -> {
                ElementInteractionJsonAdapter(
                    moshi.adapter(InteractionHandler::class.java)
                )
            }
            InteractionHandler::class.java -> {
                InteractionHandlerJsonAdapter(
                    moshi.adapter(NavigationInteractionHandler::class.java)
                )
            }
            NavigationInteractionHandler::class.java -> {
                NavigationInteractionHandlerJsonAdapter()
            }
            else -> {
                return null
            }
        }

    }
}
