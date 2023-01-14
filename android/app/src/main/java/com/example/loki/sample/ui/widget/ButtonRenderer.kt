package com.example.loki.sample.ui.widget

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.loki.sample.helper.toColor
import dev.aungkyawpaing.loki.model.ButtonStyle
import dev.aungkyawpaing.loki.model.ButtonStyle.FILLED
import dev.aungkyawpaing.loki.model.ButtonStyle.OUTLINED
import dev.aungkyawpaing.loki.model.ButtonStyle.TEXT
import dev.aungkyawpaing.loki.model.metadata.ElementStyle
import dev.aungkyawpaing.loki.model.metadata.Padding
import dev.aungkyawpaing.loki.model.metadata.TextStyle
import dev.aungkyawpaing.loki.model.Button as ButtonElement
import dev.aungkyawpaing.loki.model.Text as TextElement

@Composable
fun ButtonRenderer(element: ButtonElement) {
  val textElement = TextElement(
    text = element.text,
    textStyle = TextStyle(
      textColor = element.textStyle.textColor,
      textSize = element.textStyle.textSize,
      isBold = element.textStyle.isBold
    )
  )

  when (element.buttonStyle) {
    FILLED -> Button(
      shape = CircleShape,
      modifier = element.style?.asModifier() ?: Modifier,
      colors = ButtonDefaults.buttonColors(backgroundColor = element.color.toColor()),
      onClick = {}
    ) {
      CompositeRenderer(element = textElement)
    }
    OUTLINED -> OutlinedButton(
      shape = CircleShape,
      border = BorderStroke(1.dp, element.color.toColor()),
      modifier = element.style?.asModifier() ?: Modifier,
      onClick = {}
    ) {
      CompositeRenderer(element = textElement)
    }
    TEXT -> TextButton(
      modifier = element.style?.asModifier() ?: Modifier,
      colors = ButtonDefaults.buttonColors(backgroundColor = element.color.toColor()),
      onClick = {}
    ) {
      CompositeRenderer(element = textElement)
    }
  }
}

@Preview(name = "TextButton", "Button Components")
@Composable
fun TextButtonRendererPreview() {
  ButtonRenderer(
    element = ButtonElement(
      text = "Submit",
      textStyle = TextStyle(textSize = 14),
      buttonStyle = TEXT,
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
}

@Preview(name = "FilledButton", "Button Components")
@Composable
fun FilledButtonRendererPreview() {
  ButtonRenderer(
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
}

@Preview(name = "OutlinedButton", "Button Components")
@Composable
fun OutlinedButtonRendererPreview() {
  ButtonRenderer(
    element = ButtonElement(
      text = "Submit",
      textStyle = TextStyle(textSize = 14),
      buttonStyle = OUTLINED,
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
}
