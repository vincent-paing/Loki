package com.example.loki.sample.ui.widget

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.aungkyawpaing.loki.model.Button
import dev.aungkyawpaing.loki.model.ButtonStyle.FILLED
import dev.aungkyawpaing.loki.model.metadata.ElementStyle
import dev.aungkyawpaing.loki.model.metadata.Padding
import dev.aungkyawpaing.loki.model.Row as RowElement
import dev.aungkyawpaing.loki.model.Text as TextElement
import dev.aungkyawpaing.loki.model.metadata.TextStyle

@Composable
fun RowRenderer(element: RowElement) {
  Row(
    modifier = element.style?.asModifier() ?: Modifier,
  ) {
    element.children.forEach { child ->
      CompositeRenderer(element = child)
    }
  }
}

@Preview(name = "Row", "Layout Components")
@Composable
fun RowRendererPreview() {
  RowRenderer(
    element = RowElement(
      children = listOf(
        TextElement(
          text = "Lorem",
          textStyle = TextStyle(
            textSize = 24,
            isBold = true,
          ), style = null
        ),
        TextElement(
          text = "ipsum",
          textStyle = TextStyle(
            textSize = 14,
            isBold = false,
          ),
          style = ElementStyle(
            padding = Padding(
              top = 12,
              bottom = 12,
              left = 12,
              right = 12
            )
          )
        ),
        Button(
          text = "Submit",
          textStyle = TextStyle(textSize = 20),
          buttonStyle = FILLED,
          color = "#5accf6"
        )
      )
    )
  )
}
