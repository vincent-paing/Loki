package dev.aungkyawpaing.loki.model

enum class LokiElementType(val typeString: String) {
    TEXT("Text"),
    IMAGE("Image"),
    ROW("Row"),
    COLUMN("Column");

    companion object {
        fun fromTypeString(typeString: String?) = values().find { it.typeString == typeString }
    }
}