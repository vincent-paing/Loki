package com.example.loki.sample.interaction

import dev.aungkyawpaing.loki.model.interaction.InteractionHandler
import dev.aungkyawpaing.loki.model.interaction.NavigationInteractionHandler

fun CompositeInteractor(interactionHandler: InteractionHandler) {

  when (interactionHandler) {
    is NavigationInteractionHandler -> {
      NavigationInteractor(interactionHandler)
    }
  }
}