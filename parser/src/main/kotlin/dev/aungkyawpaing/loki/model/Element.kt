package dev.aungkyawpaing.loki.model

import dev.aungkyawpaing.loki.model.metadata.ElementStyle

abstract class Element(
    open val type: LokiElementType,
    open val style: ElementStyle?
)