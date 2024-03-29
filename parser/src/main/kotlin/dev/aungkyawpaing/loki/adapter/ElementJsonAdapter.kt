package dev.aungkyawpaing.loki.adapter

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import dev.aungkyawpaing.loki.model.*

class ElementJsonAdapter(
    private val buttonJsonAdapter: JsonAdapter<Button>,
    private val textJsonAdapter: JsonAdapter<Text>,
    private val imageJsonAdapter: JsonAdapter<Image>,
    private val rowJsonAdapter: JsonAdapter<Row>,
    private val columnJsonAdapter: JsonAdapter<Column>,
    private val cardJsonAdapter: JsonAdapter<Card>,
    private val lazyListJsonAdapter: JsonAdapter<LazyList>,
    private val lazyGridJsonAdapter: JsonAdapter<LazyGrid>
) : JsonAdapter<Element>() {

    companion object {
        private const val KEY_TYPE = "type"
    }

    override fun fromJson(reader: JsonReader): Element? {
        var element: Element? = null

        while (reader.hasNext()) {
            val jsonValueMap = reader.readJsonValue() as Map<*, *>
            val typeString = jsonValueMap[KEY_TYPE] as String

            when (LokiElementType.fromTypeString(typeString)) {
                LokiElementType.Button-> {
                    element = buttonJsonAdapter.fromJsonValue(jsonValueMap)
                }
                LokiElementType.TEXT -> {
                    element = textJsonAdapter.fromJsonValue(jsonValueMap)
                }
                LokiElementType.IMAGE -> {
                    element = imageJsonAdapter.fromJsonValue(jsonValueMap)
                }
                LokiElementType.ROW -> {
                    element = rowJsonAdapter.fromJsonValue(jsonValueMap)
                }
                LokiElementType.COLUMN -> {
                    element = columnJsonAdapter.fromJsonValue(jsonValueMap)
                }
                LokiElementType.CARD -> {
                    element = cardJsonAdapter.fromJsonValue(jsonValueMap)
                }
                LokiElementType.LAZY_LIST -> {
                    element = lazyListJsonAdapter.fromJsonValue(jsonValueMap)
                }
                LokiElementType.LAZY_GRID -> {
                    element = lazyGridJsonAdapter.fromJsonValue(jsonValueMap)
                }
                null -> {
                    throw IllegalArgumentException("Illegal type: $typeString found. Refer to Loki spec")
                }
            }
        }

        return element
    }

    override fun toJson(writer: JsonWriter, value: Element?) {
        if (value == null) {
            writer.nullValue()
        } else {
            when (value.type) {
                LokiElementType.Button-> {
                    buttonJsonAdapter.toJson(writer, value as Button)
                }
                LokiElementType.TEXT -> {
                    textJsonAdapter.toJson(writer, value as Text)
                }
                LokiElementType.IMAGE -> {
                    imageJsonAdapter.toJson(writer, value as Image)
                }
                LokiElementType.ROW -> {
                    rowJsonAdapter.toJson(writer, value as Row)
                }
                LokiElementType.COLUMN -> {
                    columnJsonAdapter.toJson(writer, value as Column)
                }
                LokiElementType.CARD -> {
                    cardJsonAdapter.toJson(writer, value as Card)
                }
                LokiElementType.LAZY_LIST -> {
                    lazyListJsonAdapter.toJson(writer, value as LazyList)
                }
                LokiElementType.LAZY_GRID -> {
                    lazyGridJsonAdapter.toJson(writer, value as LazyGrid)
                }
            }
        }
    }
}