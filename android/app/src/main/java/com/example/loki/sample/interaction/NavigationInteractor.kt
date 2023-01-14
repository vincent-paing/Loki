package com.example.loki.sample.interaction

import com.example.loki.sample.ui.navigation.Navigator
import com.example.loki.sample.ui.navigation.Screen
import dev.aungkyawpaing.loki.model.interaction.NavigationInteractionHandler

fun NavigationInteractor(interaction: NavigationInteractionHandler) {
  Navigator.navigate(Screen(interaction.page))
}
