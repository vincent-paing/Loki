package com.example.loki.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.airbnb.android.showkase.models.Showkase
import com.example.loki.sample.data.LokiRepositoryFirebaseImpl
import com.example.loki.sample.data.LokiRepositoryLocalImpl
import com.example.loki.sample.ui.navigation.Navigator
import com.example.loki.sample.ui.theme.LokiSampleTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      LokiSampleTheme {
        Scaffold() { paddingValues ->

          val navController = rememberNavController()
          val destination by Navigator.destination.collectAsState()

          LaunchedEffect(destination) {
            if (navController.currentDestination?.route != destination.route) {
              navController.navigate(destination.route)
            }
          }

          NavHost(
            navController = navController,
            startDestination = "home"
          ) {
            composable("home") {
              Box(modifier = Modifier.padding(paddingValues)) {
                HomePage(
                  repository = LokiRepositoryFirebaseImpl()
                )
              }
            }
            composable("detail") {
              Box(modifier = Modifier.padding(paddingValues)) {
                DetailPage(
                  repository = LokiRepositoryFirebaseImpl()
                )
              }
            }
          }
        }
      }
    }
  }
}