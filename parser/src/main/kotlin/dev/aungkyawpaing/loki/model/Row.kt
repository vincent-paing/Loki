package dev.aungkyawpaing.loki.model

import dev.aungkyawpaing.loki.model.metadata.ElementStyle

data class Row(
    val children: List<Element>,
    override val style: ElementStyle? = null
) : Element(LokiElementType.ROW, style)