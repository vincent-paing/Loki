package dev.aungkyawpaing.loki.adapter

import dev.aungkyawpaing.loki.getJsonAdapter
import dev.aungkyawpaing.loki.model.Button
import dev.aungkyawpaing.loki.model.ButtonStyle
import dev.aungkyawpaing.loki.model.metadata.ElementStyle
import dev.aungkyawpaing.loki.model.metadata.TextStyle
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.skyscreamer.jsonassert.JSONAssert

class ButtonJsonAdapterTest {
    private val adapter = getJsonAdapter<Button>()

    @Nested
    @DisplayName("fromJson")
    inner class FromJson {
        @Test
        fun `parse Button correctly`() {
            val json = """
            {
                "type": "Button",
                "text": "some-text",
                "textStyle": {
                  "textSize": 12
                },
                "buttonStyle": "filled",
                "color": "#FFFFFF"
            }
            """.trimIndent()
            val expected = Button(
                text = "some-text",
                textStyle = TextStyle(textSize = 12),
                buttonStyle = ButtonStyle.FILLED,
                color = "#FFFFFF"
            )
            val actual = adapter.fromJson(json)

            Assertions.assertEquals(expected, actual)
        }

        @Test
        fun `parse style if it exists`() {
            val json = """
            {
                "type": "Button",
                "text": "some-text",
                "textStyle": {
                  "textSize": 12
                },
                "buttonStyle": "filled",
                "color": "#FFFFFF",
                "style" : {}
            }
            """.trimIndent()
            val expected = Button(
                text = "some-text",
                textStyle = TextStyle(textSize = 12),
                buttonStyle = ButtonStyle.FILLED,
                color = "#FFFFFF",
                style = ElementStyle()
            )
            val actual = adapter.fromJson(json)

            Assertions.assertEquals(expected, actual)
        }

        @Test
        fun `throws error when text is missing`() {
            val json = """
            {
              "type": "Button",
              "textStyle": {
                "textSize": 12
              },
              "buttonStyle": "filled",
              "color": "#FFFFFF"
            }
        """.trimIndent()

            val exception = Assertions.assertThrows(
                IllegalArgumentException::class.java
            ) { adapter.fromJson(json) }
            Assertions.assertEquals("Required property text is missing", exception.message)
        }

        @Test
        fun `throws error when textStyle is missing`() {
            val json = """
            {
              "type": "Button",
              "text": "some-text",
              "buttonStyle": "filled",
              "color": "#FFFFFF"
            }
        """.trimIndent()

            val exception = Assertions.assertThrows(
                IllegalArgumentException::class.java
            ) { adapter.fromJson(json) }
            Assertions.assertEquals("Required property textStyle is missing", exception.message)
        }

        @Test
        fun `throws error when buttonStyle is missing`() {
            val json = """
            {
              "type": "Button",
              "text": "some-text",
              "textStyle": {
                "textSize": 12
              },
              "color": "#FFFFFF"
            }
        """.trimIndent()

            val exception = Assertions.assertThrows(
                IllegalArgumentException::class.java
            ) { adapter.fromJson(json) }
            Assertions.assertEquals("Required property buttonStyle is missing", exception.message)
        }

        @Test
        fun `throws error when color is missing`() {
            val json = """
            {
              "type": "Button",
              "text": "some-text",
              "textStyle": {
                "textSize": 12
              },
              "buttonStyle": "filled"
            }
        """.trimIndent()

            val exception = Assertions.assertThrows(
                IllegalArgumentException::class.java
            ) { adapter.fromJson(json) }
            Assertions.assertEquals("Required property color is missing", exception.message)
        }
    }

    @Nested
    @DisplayName("toJson")
    inner class ToJson {

        @Test
        fun `write to json`() {
            val button = Button(
                text = "some-text",
                textStyle = TextStyle(textSize = 12),
                buttonStyle = ButtonStyle.FILLED,
                color = "#FFFFFF"
            )
            val expectedJson = """
            {
                "type": "Button",
                "text": "some-text",
                "textStyle": {
                  "textSize": 12
                },
                "buttonStyle": "filled",
                "color": "#FFFFFF"
            }
            """.trimIndent()

            val actual = adapter.toJson(button)

            JSONAssert.assertEquals(expectedJson, actual, false)
        }
    }

}