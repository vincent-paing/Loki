package dev.aungkyawpaing.loki.model

enum class LokiElementType(val typeString: String) {
    TEXT("Text"),
    IMAGE("Image"),
    ROW("Row"),
    COLUMN("Column"),
    CARD("Card"),
    LAZY_LIST("LazyList");

    companion object {
        fun fromTypeString(typeString: String?) = values().find { it.typeString == typeString }
    }
}