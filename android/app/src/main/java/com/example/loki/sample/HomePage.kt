package com.example.loki.sample

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.loki.sample.data.LokiRepository
import com.example.loki.sample.ui.widget.RenderFromJson

@Composable
fun HomePage(
  repository: LokiRepository
) {

  val data = repository.getMainPageJson().collectAsState(initial = null).value


  if (data != null) {
    Box(
      modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
      RenderFromJson(json = data)
    }
  }
}
