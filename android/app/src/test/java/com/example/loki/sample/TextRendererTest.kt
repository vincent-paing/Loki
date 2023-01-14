//package com.example.loki.sample
//
//import androidx.compose.ui.test.assertIsDisplayed
//import androidx.compose.ui.test.junit4.createComposeRule
//import androidx.compose.ui.test.onNodeWithText
//import androidx.test.ext.junit.runners.AndroidJUnit4
//import com.example.loki.sample.ui.widget.TextRenderer
//import dev.aungkyawpaing.loki.model.Text
//import dev.aungkyawpaing.loki.model.metadata.TextStyle
//import org.junit.Rule
//import org.junit.Test
//import org.junit.runner.RunWith
//
//@RunWith(AndroidJUnit4::class)
//class TextRendererTest {
//
//  @get:Rule
//  val composeTestRule = createComposeRule()
//
//  val textElement = Text(
//    text = "Some Text",
//    textStyle = TextStyle(
//      textSize = 20,
//      isBold = false,
//    ), style = null
//  )
//
//  @Test
//  fun renderText() {
//    composeTestRule.setContent {
//      TextRenderer(textElement = textElement)
//    }
//
//    composeTestRule.onNodeWithText(textElement.text).assertIsDisplayed()
//  }
//}