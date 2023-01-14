package dev.aungkyawpaing.loki.model

import dev.aungkyawpaing.loki.model.metadata.ElementStyle
import dev.aungkyawpaing.loki.model.metadata.Orientation


data class LazyList(
    val orientation: Orientation,
    val children: List<LazyElement>,
    override val style: ElementStyle? = null
) : Element(LokiElementType.LAZY_LIST, style)