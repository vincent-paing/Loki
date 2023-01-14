package dev.aungkyawpaing.loki.model

enum class LokiElementType(val typeString: String) {
    Button("Button"),
    TEXT("Text"),
    IMAGE("Image"),
    ROW("Row"),
    COLUMN("Column"),
    CARD("Card"),
    LAZY_LIST("LazyList"),
    LAZY_GRID("LazyGrid");

    companion object {
        fun fromTypeString(typeString: String?) = values().find { it.typeString == typeString }
    }
}