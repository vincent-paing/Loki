package com.example.loki.sample.ui.widget

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.aungkyawpaing.loki.model.ButtonStyle.FILLED
import dev.aungkyawpaing.loki.model.LazyElement
import dev.aungkyawpaing.loki.model.LazyGrid
import dev.aungkyawpaing.loki.model.metadata.ElementStyle
import dev.aungkyawpaing.loki.model.metadata.Length
import dev.aungkyawpaing.loki.model.metadata.Orientation
import dev.aungkyawpaing.loki.model.metadata.Orientation.HORIZONTAL
import dev.aungkyawpaing.loki.model.metadata.Orientation.VERTICAL
import dev.aungkyawpaing.loki.model.metadata.Padding
import dev.aungkyawpaing.loki.model.metadata.TextStyle
import dev.aungkyawpaing.loki.model.Button as ButtonElement
import dev.aungkyawpaing.loki.model.Text as TextElement

@Composable
fun LazyGridRenderer(element: LazyGrid) {

  when (element.orientation) {
    HORIZONTAL -> {
      LazyHorizontalGrid(
        modifier = element.style?.asModifier() ?: Modifier,
        rows = GridCells.Fixed(element.numOfRowColumn)
      ) {
        items(element.children, key = LazyElement::id) {
          CompositeRenderer(it.element)
        }
      }
    }
    Orientation.VERTICAL -> {
      LazyVerticalGrid(
        modifier = element.style?.asModifier() ?: Modifier,
        columns = GridCells.Fixed(element.numOfRowColumn),
        userScrollEnabled = false
      ) {
        items(element.children, key = LazyElement::id) {
          CompositeRenderer(it.element)
        }
      }
    }
  }
}

@Preview(name = "HORIZONTAL Lazy Grid", "Layout Components")
@Composable
fun LazyGridHorizontalRendererPreview() {
  LazyGridRenderer(
    element = LazyGrid(
      orientation = HORIZONTAL,
      numOfRowColumn = 4,
      style = ElementStyle(
        width = Length.Number(200),
        height = Length.Number(300),
        padding = Padding(
          top = 12,
          bottom = 12,
          left = 12,
          right = 12
        )
      ),
      children = listOf(
        LazyElement(
          id = "1",
          element = dev.aungkyawpaing.loki.model.Text(
            text = "Lorem",
            textStyle = TextStyle(
              textSize = 24,
              isBold = true,
            ),
            style = ElementStyle(
              padding = Padding(
                top = 12,
                bottom = 12,
                left = 12,
                right = 12
              )
            )
          )
        ),
        LazyElement(
          id = "2",
          element = dev.aungkyawpaing.loki.model.Text(
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
          )
        ),
        LazyElement(
          id = "3",
          element = dev.aungkyawpaing.loki.model.Text(
            text = "dolor sit amet",
            textStyle = TextStyle(
              textSize = 10,
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
          )
        ),
        LazyElement(
          id = "4",
          element = dev.aungkyawpaing.loki.model.Button(
            text = "Submit",
            textStyle = TextStyle(textSize = 14),
            buttonStyle = FILLED,
            color = "#5accf6",
            style = ElementStyle(
              padding = Padding(
                top = 12,
                bottom = 12,
                left = 12,
                right = 12
              )
            )
          )
        )
      ),
    )
  )
}

@Preview(name = "VERTICAL Lazy Grid", "Layout Components")
@Composable
fun LazyGridVerticalRendererPreview() {
  LazyGridRenderer(
    element = LazyGrid(
      orientation = VERTICAL,
      numOfRowColumn = 2,
      style = ElementStyle(
        padding = Padding(
          top = 12,
          bottom = 12,
          left = 12,
          right = 12
        )
      ),
      children = listOf(
        LazyElement(
          id = "1",
          element = dev.aungkyawpaing.loki.model.Text(
            text = "Lorem",
            textStyle = TextStyle(
              textSize = 24,
              isBold = true,
            ),
            style = ElementStyle(
              padding = Padding(
                top = 12,
                bottom = 12,
                left = 12,
                right = 12
              )
            )
          )
        ),
        LazyElement(
          id = "2",
          element = dev.aungkyawpaing.loki.model.Text(
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
          )
        ),
        LazyElement(
          id = "3",
          element = dev.aungkyawpaing.loki.model.Text(
            text = "dolor sit amet",
            textStyle = TextStyle(
              textSize = 10,
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
          )
        ),
        LazyElement(
          id = "4",
          element = dev.aungkyawpaing.loki.model.Button(
            text = "Submit",
            textStyle = TextStyle(textSize = 14),
            buttonStyle = FILLED,
            color = "#5accf6",
            style = ElementStyle(
              padding = Padding(
                top = 12,
                bottom = 12,
                left = 12,
                right = 12
              )
            )
          )
        )
      ),
    )
  )
}
