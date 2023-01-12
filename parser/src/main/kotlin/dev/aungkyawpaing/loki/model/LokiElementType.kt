package dev.aungkyawpaing.loki.model

enum class LokiElementType(val typeString: String) {
    Element("Element"),
    TEXT("Text");

    companion object {
        fun fromTypeString(typeString: String?) = values().find { it.typeString == typeString }
    }
}