package dev.aungkyawpaing.loki.model

import dev.aungkyawpaing.loki.model.metadata.ElementStyle
import dev.aungkyawpaing.loki.model.metadata.TextStyle

data class Button(
    val text: String,
    val textStyle: TextStyle,
    val buttonStyle: ButtonStyle,
    val color: String,
    override val style: ElementStyle? = null
) : Element(LokiElementType.Button, style)

enum class ButtonStyle {
    FILLED,
    OUTLINED,
    TEXT
}