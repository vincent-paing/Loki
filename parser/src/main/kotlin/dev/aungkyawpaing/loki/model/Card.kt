package dev.aungkyawpaing.loki.model

import dev.aungkyawpaing.loki.model.metadata.ElementStyle

data class Card(
    val cornerRadius: Int = 0,
    val child: Element,
    override val style: ElementStyle? = null
) : Element(LokiElementType.CARD, style)