package com.example.loki.sample.ui.widget

import androidx.compose.runtime.Composable
import dev.aungkyawpaing.loki.model.Button
import dev.aungkyawpaing.loki.model.Card
import dev.aungkyawpaing.loki.model.Column
import dev.aungkyawpaing.loki.model.Element
import dev.aungkyawpaing.loki.model.Image
import dev.aungkyawpaing.loki.model.LazyGrid
import dev.aungkyawpaing.loki.model.LazyList
import dev.aungkyawpaing.loki.model.Row
import dev.aungkyawpaing.loki.model.Text

@Composable
fun CompositeRenderer(element: Element) {
  when (element) {
    is Text -> {
      TextRenderer(textElement = element)
    }
    is Image -> {
      ImageRenderer(imgElement = element)
    }
    is LazyList -> {
      LazyListRenderer(element = element)
    }
    is LazyGrid -> {
      LazyGridRenderer(element = element)
    }
    is Column -> {
      ColumnRenderer(element = element)
    }
    is Row -> {
      RowRenderer(element = element)
    }
    is Card -> {
      CardRenderer(element = element)
    }
    is Button -> {
      ButtonRenderer(element = element)
    }

  }
}