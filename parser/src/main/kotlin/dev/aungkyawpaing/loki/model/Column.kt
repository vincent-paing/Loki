package dev.aungkyawpaing.loki.model

import dev.aungkyawpaing.loki.model.interaction.ElementInteractions
import dev.aungkyawpaing.loki.model.metadata.ElementStyle

data class Column(
    val children: List<Element>,
    override val style: ElementStyle? = null,
    override val interactions: ElementInteractions? = null
) : Element(LokiElementType.COLUMN, style, interactions)