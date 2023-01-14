package com.example.loki.sample.ui.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.aungkyawpaing.loki.model.Button as ButtonElement
import dev.aungkyawpaing.loki.model.ButtonStyle
import dev.aungkyawpaing.loki.model.Element
import dev.aungkyawpaing.loki.model.Text as TextElement
import dev.aungkyawpaing.loki.model.metadata.ElementStyle
import dev.aungkyawpaing.loki.model.metadata.Length
import dev.aungkyawpaing.loki.model.metadata.Length.Number
import dev.aungkyawpaing.loki.model.metadata.Padding
import dev.aungkyawpaing.loki.model.metadata.TextStyle
import okhttp3.internal.immutableListOf
import dev.aungkyawpaing.loki.model.Column as ColumnElement

@Composable
fun ColumnRenderer(element: ColumnElement) {
  Column(
    modifier = element.asModifier(),
  ) {
    element.children.forEach { child ->
      CompositeRenderer(element = child)
    }
  }
}

@Preview(name = "Column", "Layout Components")
@Composable
fun Column() {
  ColumnRenderer(
    element = ColumnElement(
      children = listOf(
        TextElement(
          text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
          textStyle = TextStyle(
            textSize = 24,
            isBold = true,
          ), style = null
        ),
        TextElement(
          text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut ullamcorper sodales erat vel egestas. In nec diam non est volutpat convallis et ut urna. Aliquam ante libero, sollicitudin dictum magna sit amet, pharetra semper justo. Sed gravida, odio vitae iaculis dignissim, turpis metus faucibus nisi, non aliquam tellus erat et tortor. Duis egestas metus in nisi scelerisque, eu gravida lectus iaculis. Morbi eu nisl dolor. Nullam vel turpis porttitor tellus consequat lobortis. Cras lobortis lectus vel turpis feugiat, ut euismod odio venenatis.",
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
        ButtonElement(
          text = "Submit",
          textStyle = TextStyle(textSize = 20),
          buttonStyle = ButtonStyle.FILLED,
          color = "#5accf6"
        )
      )
    )
  )
}