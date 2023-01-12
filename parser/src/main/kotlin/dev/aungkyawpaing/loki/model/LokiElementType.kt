package dev.aungkyawpaing.loki.model

enum class LokiElementType(val typeString: String) {
    TEXT("Text");

    companion object {
        fun fromTypeString(typeString: String?) = values().find { it.typeString == typeString }
    }
}