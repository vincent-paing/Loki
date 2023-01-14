package com.example.loki.sample.ui.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import dev.aungkyawpaing.loki.model.metadata.ElementStyle
import dev.aungkyawpaing.loki.model.metadata.Length
import dev.aungkyawpaing.loki.model.metadata.Padding
import dev.aungkyawpaing.loki.model.Image as ImageElement

@Composable
fun ImageRenderer(imgElement: ImageElement) {

  AsyncImage(
    modifier = imgElement.style?.asModifier() ?: Modifier,
    model = ImageRequest.Builder(LocalContext.current)
      .data(imgElement.url)
      .build(),
    contentDescription = imgElement.altText,
    contentScale = ContentScale.FillBounds
  )
}

@Preview(name = "Image", "Layout Components")
@Composable
fun ImageRendererPreview() {
  ImageRenderer(
    imgElement = ImageElement(
      altText = "some altText",
      url = "https://images.google.com/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png",
      style = ElementStyle(
        width = Length.Number(48),
        height = Length.Number(48),
        padding = Padding(
          left = 24,
          right = 24,
          top = 24,
          bottom = 24,
        )
      )
    )
  )
}