package dev.aungkyawpaing.loki.model

import dev.aungkyawpaing.loki.model.metadata.ElementStyle

data class Image(
    val url: String,
    val altText: String?,
    override val style: ElementStyle?,
) : AbstractElement(LokiElementType.IMAGE, style)