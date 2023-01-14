package com.example.loki.sample.ui.widget

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.loki.sample.helper.toColor
import dev.aungkyawpaing.loki.model.Text as TextElement
import dev.aungkyawpaing.loki.model.metadata.TextStyle

@Composable
fun TextRenderer(textElement: TextElement) {

  Text(
    text = textElement.text,
    fontSize = textElement.textStyle.textSize.sp,
    fontWeight = if (textElement.textStyle.isBold == true) FontWeight.Bold else FontWeight.Normal,
    color = textElement.textStyle.textColor?.toColor() ?: Color.Unspecified,
    modifier = textElement.style?.asModifier() ?: Modifier
  )
}

@Preview(name = "Text", "Text Components")
@Composable
fun TextRendererPreview() {
  TextRenderer(
    textElement = TextElement(
      text = "Some Text", textStyle = TextStyle(
        textSize = 20,
        isBold = false,
      ), style = null
    )
  )
}