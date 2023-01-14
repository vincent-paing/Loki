package com.example.loki.sample.ui.widget

import androidx.compose.runtime.Composable
import com.example.loki.sample.MoshiInstance
import dev.aungkyawpaing.loki.model.Element

@Composable
fun RenderFromJson(json: String){
  val element = MoshiInstance.moshi.adapter(Element::class.java).fromJson(json)!!
  CompositeRenderer(element)
}