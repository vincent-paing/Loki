package com.example.loki.sample.ui.widget

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.aungkyawpaing.loki.model.Text as TextElement
import dev.aungkyawpaing.loki.model.metadata.ElementStyle
import dev.aungkyawpaing.loki.model.metadata.Length
import dev.aungkyawpaing.loki.model.metadata.Padding
import dev.aungkyawpaing.loki.model.metadata.TextStyle
import dev.aungkyawpaing.loki.model.Card as CardElement

@Composable
fun CardRenderer(element: CardElement) {

  Card(
    modifier = element.style?.asModifier() ?: Modifier,
    shape = RoundedCornerShape(element.cornerRadius.dp)
  ) {
    CompositeRenderer(element = element.child)
  }
}

@Preview(name = "Card", "Layout Components")
@Composable
fun CardRendererPreview() {
  CardRenderer(
    element = CardElement(
      style = ElementStyle(
        width = Length.Number(300),
        height = Length.Number(350),
        padding = Padding(
          top = 12,
          bottom = 12,
          left = 12,
          right = 12
        )
      ),
      cornerRadius = 10,
      child = TextElement(
        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut ullamcorper sodales erat vel egestas. In nec diam non est volutpat convallis et ut urna. Aliquam ante libero, sollicitudin dictum magna sit amet, pharetra semper justo. Sed gravida, odio vitae iaculis dignissim, turpis metus faucibus nisi, non aliqui scelerisque, eu grvel turpis porttitor tellnenatis.",
        textStyle = TextStyle(
          textSize = 20,
          isBold = false,
        ), style = ElementStyle(
          width = Length.Number(60),
          height = Length.Number(30),
          padding = Padding(
            top = 12,
            bottom = 12,
            left = 12,
            right = 12
          )
        )
      )
    )
  )
}
