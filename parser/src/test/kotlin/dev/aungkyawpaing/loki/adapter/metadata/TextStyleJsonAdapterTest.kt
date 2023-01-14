package dev.aungkyawpaing.loki.adapter.metadata

import dev.aungkyawpaing.loki.getJsonAdapter
import dev.aungkyawpaing.loki.model.metadata.TextStyle
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.skyscreamer.jsonassert.JSONAssert


class TextStyleJsonAdapterTest {

    val adapter = getJsonAdapter<TextStyle>()

    @Nested
    @DisplayName("fromJson")
    inner class FromJson {

        @Test
        fun `parse text style correctly`() {
            val json = """
            {
              "textSize": 12,
              "isBold": true,
              "textColor": "#FFFFFF"
            }
        """.trimIndent()
            val expected = TextStyle(
                textSize = 12,
                isBold = true,
                textColor = "#FFFFFF"
            )

            val actual = adapter.fromJson(json)

            Assertions.assertEquals(expected, actual)
        }

        @Test
        fun `throws error when textSize is missing`() {
            val json = """
            {
              "isBold": true
            }
        """.trimIndent()

            val exception = Assertions.assertThrows(
                IllegalArgumentException::class.java
            ) { adapter.fromJson(json) }


            Assertions.assertEquals("Required property textStyle is missing", exception.message)
        }

        @Test
        fun `set isBold as null when it is missing`() {
            val json = """
            {
              "textSize": 12
            }
        """.trimIndent()

            val actual = adapter.fromJson(json)!!.isBold

            Assertions.assertNull(actual)
        }

        @Test
        fun `set textColor as null when it is missing`() {
            val json = """
            {
              "textSize": 12
            }
        """.trimIndent()

            val actual = adapter.fromJson(json)!!.textColor

            Assertions.assertNull(actual)
        }

    }

    @Nested
    @DisplayName("toJson")
    inner class ToJson {

        @Test
        fun `write to json`() {

            val textStyle = TextStyle(
                textSize = 12,
                isBold = true,
                textColor = "#FFFFFF"
            )

            val expectedJson = """
            {
              "textSize": 12,
              "isBold": true,
              "textColor": "#FFFFFF"
            }
        """.trimIndent()

            val actual = adapter.toJson(textStyle)

            JSONAssert.assertEquals(expectedJson, actual, false)
        }


    }
}