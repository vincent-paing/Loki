package dev.aungkyawpaing.loki.adapter

import com.squareup.moshi.JsonDataException
import dev.aungkyawpaing.loki.getJsonAdapter
import dev.aungkyawpaing.loki.model.Text
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
                )
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
                JsonDataException::class.java
            ) { adapter.fromJson(json) }


            Assertions.assertEquals(exception.message, "Required property text is missing")
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
                JsonDataException::class.java
            ) { adapter.fromJson(json) }


            Assertions.assertEquals(exception.message, "Required property textStyle is missing")
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
                )
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