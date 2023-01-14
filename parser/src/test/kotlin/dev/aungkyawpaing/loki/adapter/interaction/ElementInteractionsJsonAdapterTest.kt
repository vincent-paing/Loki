package dev.aungkyawpaing.loki.adapter.interaction

import dev.aungkyawpaing.loki.getJsonAdapter
import dev.aungkyawpaing.loki.model.interaction.ElementInteractions
import dev.aungkyawpaing.loki.model.interaction.NavigationInteractionHandler
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.skyscreamer.jsonassert.JSONAssert

class ElementInteractionsJsonAdapterTest {

    val adapter = getJsonAdapter<ElementInteractions>()

    @Nested
    @DisplayName("fromJson")
    inner class FromJson {
        @Test
        fun `parse correctly`() {
            val json = """
            {
              "onPress": {
                "type": "Navigation",
                "page": "home"
              }
            }
        """.trimIndent()
            val expected = ElementInteractions(
                onPress = NavigationInteractionHandler(
                    page = "home"
                )
            )

            val actual = adapter.fromJson(json)

            Assertions.assertEquals(expected, actual)
        }

        @Test
        fun `set onPress as null if it doesn't exist`() {
            val json = """
            {
            }
        """.trimIndent()

            val expected = ElementInteractions(
                onPress = null
            )

            val actual = adapter.fromJson(json)

            Assertions.assertEquals(expected, actual)
        }


    }

    @Nested
    @DisplayName("toJson")
    inner class ToJson {

        @Test
        fun `write to json`() {
            val elementInteractions = ElementInteractions(
                onPress = NavigationInteractionHandler(
                    page = "home"
                )
            )
            val expected = """
            {
              "onPress": {
                "type": "Navigation",
                "page": "home"
              }
            }
        """.trimIndent()

            val actual = adapter.toJson(elementInteractions)

            JSONAssert.assertEquals(expected, actual, false)
        }
    }

}