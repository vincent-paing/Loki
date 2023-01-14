package dev.aungkyawpaing.loki.model

import dev.aungkyawpaing.loki.model.metadata.ElementStyle
import dev.aungkyawpaing.loki.model.metadata.Orientation

data class LazyGrid(
    val orientation: Orientation,
    val numOfRowColumn: Int,
    val children: List<LazyElement>,
    override val style: ElementStyle? = null
) : Element(LokiElementType.LAZY_GRID, style)