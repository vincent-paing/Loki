package com.example.loki.sample.ui.widget

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.aungkyawpaing.loki.model.ButtonStyle.FILLED
import dev.aungkyawpaing.loki.model.LazyElement
import dev.aungkyawpaing.loki.model.LazyList
import dev.aungkyawpaing.loki.model.metadata.ElementStyle
import dev.aungkyawpaing.loki.model.metadata.Orientation
import dev.aungkyawpaing.loki.model.metadata.Orientation.HORIZONTAL
import dev.aungkyawpaing.loki.model.metadata.Orientation.VERTICAL
import dev.aungkyawpaing.loki.model.metadata.Padding
import dev.aungkyawpaing.loki.model.metadata.TextStyle
import dev.aungkyawpaing.loki.model.Button as ButtonElement
import dev.aungkyawpaing.loki.model.Text as TextElement

@Composable
fun LazyListRenderer(element: LazyList) {

  when (element.orientation) {
    Orientation.HORIZONTAL -> {
      LazyRow(
        modifier = element.asModifier()
      ) {
        items(element.children, key = LazyElement::id) {
          CompositeRenderer(it.element)
        }
      }
    }
    Orientation.VERTICAL -> {
      LazyColumn(
        modifier = element.asModifier()
      ) {
        items(element.children, key = LazyElement::id) {
          CompositeRenderer(it.element)
        }
      }
    }
  }
}

@Preview(name = "HORIZONTAL Lazy List", "Layout Components")
@Composable
fun LazyListHorizontalRenderer() {
  LazyListRenderer(
    element = LazyList(
      orientation = HORIZONTAL,
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
          element = TextElement(
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
          element = TextElement(
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
          element = TextElement(
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
          element = ButtonElement(
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

@Preview(name = "VERTICAL Lazy List", "Layout Components")
@Composable
fun LazyListVerticalPreview() {
  LazyListRenderer(
    element = LazyList(
      orientation = VERTICAL,
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
          element = TextElement(
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
          element = TextElement(
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
          element = TextElement(
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
          element = ButtonElement(
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
