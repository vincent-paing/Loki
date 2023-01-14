package dev.aungkyawpaing.loki.adapter.interaction

import dev.aungkyawpaing.loki.model.interaction.NavigationInteractionHandler
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.skyscreamer.jsonassert.JSONAssert

class NavigationInteractionHandlerJsonAdapterTest {

    val adapter = NavigationInteractionHandlerJsonAdapter()

    @Nested
    @DisplayName("fromJson")
    inner class FromJson {
        @Test
        fun `parse correctly`() {
            val json = """
            {
              "type": "Navigation",
              "page": "home"
            }
        """.trimIndent()
            val expected = NavigationInteractionHandler(
                page = "home"
            )

            val actual = adapter.fromJson(json)

            Assertions.assertEquals(expected, actual)
        }

        @Test
        fun `throws error when page is missing`() {
            val json = """
            {
              "type": "Navigation"
            }
        """.trimIndent()

            val exception = Assertions.assertThrows(
                IllegalArgumentException::class.java
            ) { adapter.fromJson(json) }


            Assertions.assertEquals("Required property 'page' is missing", exception.message)
        }


    }

    @Nested
    @DisplayName("toJson")
    inner class ToJson {

        @Test
        fun `write to json`() {
            val navigationInteractionHandler = NavigationInteractionHandler(
                page = "home"
            )
            val expected = """
            {
              "type": "Navigation",
              "page": "home"
            }
        """.trimIndent()

            val actual = adapter.toJson(navigationInteractionHandler)

            JSONAssert.assertEquals(expected, actual, false)
        }
    }

}