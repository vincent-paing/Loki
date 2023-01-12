package dev.aungkyawpaing.loki.model

import dev.aungkyawpaing.loki.model.metadata.TextStyle

data class Text(
    val text: String,
    val textStyle: TextStyle,
) : LokiElement(LokiElementType.TEXT)