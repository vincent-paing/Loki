package com.example.loki.sample.ui.navigation

import kotlinx.coroutines.flow.MutableStateFlow

object Navigator {

  var destination: MutableStateFlow<Screen> =
    MutableStateFlow(Screen("home"))

  fun navigate(screen: Screen) {
    this.destination.value = screen
  }

}