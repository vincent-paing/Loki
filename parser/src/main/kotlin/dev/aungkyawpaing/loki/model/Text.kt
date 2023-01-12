package dev.aungkyawpaing.loki.model

data class Text(
    val text: String,
    val textStyle: TextStyle,
) : LokiElement(LokiElementType.TEXT)