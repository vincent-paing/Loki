package dev.aungkyawpaing.loki.adapter


import dev.aungkyawpaing.loki.getJsonAdapter
import dev.aungkyawpaing.loki.model.Text
import dev.aungkyawpaing.loki.model.metadata.ElementStyle
import dev.aungkyawpaing.loki.model.metadata.TextStyle
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.skyscreamer.jsonassert.JSONAssert

class TextJsonAdapterTest {

    private val adapter = getJsonAdapter<Text>()

    @Nested
    @DisplayName("fromJson")
    inner class FromJson {
        @Test
        fun `parse text correctly`() {
            val json = """
            {
              "type": "Text",
              "text": "Some Text",
              "textStyle": {
                "textSize": 12,
                "isBold": true
              }
            }
        """.trimIndent()
            val expected = Text(
                text = "Some Text",
                textStyle = TextStyle(
                    textSize = 12,
                    isBold = true,
                ),
                style = null
            )

            val actual = adapter.fromJson(json)

            Assertions.assertEquals(expected, actual)
        }

        @Test
        fun `parse style if it exists`() {
            val json = """
            {
              "type": "Text",
              "text": "Some Text",
              "textStyle": {
                "textSize": 12,
                "isBold": true
              },
              "style": {}
            }
        """.trimIndent()
            val expected = Text(
                text = "Some Text",
                textStyle = TextStyle(
                    textSize = 12,
                    isBold = true,
                ),
                style = ElementStyle()
            )

            val actual = adapter.fromJson(json)

            Assertions.assertEquals(expected, actual)
        }

        @Test
        fun `throws error when text is missing`() {
            val json = """
            {
              "type": "Text",
              "textStyle": {
                "textSize": 12,
                "isBold": true
              }
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
              "type": "text",
              "text": "Some Text"
            }
        """.trimIndent()

            val exception = Assertions.assertThrows(
                IllegalArgumentException::class.java
            ) { adapter.fromJson(json) }


            Assertions.assertEquals("Required property textStyle is missing", exception.message)
        }

    }

    @Nested
    @DisplayName("toJson")
    inner class ToJson {

        @Test
        fun `write to json`() {

            val text = Text(
                text = "Some Text",
                textStyle = TextStyle(
                    textSize = 12,
                    isBold = true,
                ),
                style = null
            )

            val expectedJson = """
            {
              "type": "Text",
              "text": "Some Text",
              "textStyle": {
                "textSize": 12,
                "isBold": true
              }
            }
        """.trimIndent()

            val actual = adapter.toJson(text)

            JSONAssert.assertEquals(expectedJson, actual, false)
        }
    }

}