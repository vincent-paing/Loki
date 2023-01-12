package dev.aungkyawpaing.loki.adapter.metadata

import com.squareup.moshi.JsonDataException
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
              "isBold": true
            }
        """.trimIndent()
            val expected = TextStyle(
                textSize = 12,
                isBold = true,
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
                JsonDataException::class.java
            ) { adapter.fromJson(json) }


            Assertions.assertEquals(exception.message, "Required property textStyle is missing")
        }

        @Test
        fun `throws error when isBold is missing`() {
            val json = """
            {
              "textSize": 12
            }
        """.trimIndent()

            val exception = Assertions.assertThrows(
                JsonDataException::class.java
            ) { adapter.fromJson(json) }


            Assertions.assertEquals(exception.message, "Required property isBold is missing")
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
            )

            val expectedJson = """
            {
              "textSize": 12,
              "isBold": true
            }
        """.trimIndent()

            val actual = adapter.toJson(textStyle)

            JSONAssert.assertEquals(expectedJson, actual, false)
        }


    }
}