package com.example.loki.sample.ui.widget

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.loki.sample.interaction.CompositeInteractor
import dev.aungkyawpaing.loki.model.Element
import dev.aungkyawpaing.loki.model.metadata.ElementStyle
import dev.aungkyawpaing.loki.model.metadata.Length

@SuppressLint("ModifierFactoryExtensionFunction")
fun ElementStyle.asModifier(): Modifier {

  var modifier: Modifier = Modifier

  if (padding != null) {
    with(padding!!) {
      modifier = modifier.padding(
        start = left.dp,
        top = top.dp,
        end = right.dp,
        bottom = bottom.dp,
      )
    }
  }

  val _width = width
  modifier = when (_width) {
    is Length.Max -> {
      modifier.fillMaxWidth()
    }
    is Length.Number -> {
      modifier.width(width = _width.value.dp)
    }
    null -> {
      modifier.wrapContentWidth()
    }
  }


  val _height = height
  modifier = when (_height) {
    Length.Max -> {
      modifier.fillMaxHeight()
    }
    is Length.Number -> {
      modifier.height(_height.value.dp)
    }
    null -> {
      modifier.wrapContentHeight()
    }
  }

  return modifier
}

@SuppressLint("ModifierFactoryExtensionFunction")
@Composable
fun Element.asModifier(): Modifier {

  var modifier: Modifier = this.style?.asModifier() ?: Modifier


  val onPress = this.interactions?.onPress
  if (onPress != null) {
    modifier = modifier.clickable {
      CompositeInteractor(onPress)
    }
  }

  return modifier
}