package dev.aungkyawpaing.loki.adapter.lazylist

import dev.aungkyawpaing.loki.getJsonAdapter
import dev.aungkyawpaing.loki.model.LazyElement
import dev.aungkyawpaing.loki.model.Text
import dev.aungkyawpaing.loki.model.metadata.TextStyle
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.skyscreamer.jsonassert.JSONAssert

class LazyElementJsonAdapterTest {

    val adapter = getJsonAdapter<LazyElement>()

    @Nested
    @DisplayName("fromJson")
    inner class FromJson {

        @Test
        fun `parse lazy element`() {
            val json = """
            {
              "id": "some-id",
              "element": {
                "type": "Text",
                "text": "Some Text",
                "textStyle": {
                  "textSize": 12,
                  "isBold": true
                }
              }
            }
        """.trimIndent()
            val expected = LazyElement(
                id = "some-id",
                element = Text(
                    text = "Some Text",
                    textStyle = TextStyle(
                        textSize = 12,
                        isBold = true
                    ),
                )
            )

            val actual = adapter.fromJson(json)

            Assertions.assertEquals(expected, actual)
        }

        @Test
        fun `throws error when id is missing`() {
            val json = """
            {
              "element": {
                "type": "Text",
                "text": "Some Text",
                "textStyle": {
                  "textSize": 12,
                  "isBold": true
                }
              }
            }
        """.trimIndent()

            val exception = Assertions.assertThrows(IllegalArgumentException::class.java) {
                adapter.fromJson(json)
            }

            Assertions.assertEquals("Required property 'id' is missing", exception.message)
        }

        @Test
        fun `throws error when element is missing`() {
            val json = """
            {
              "id": "some-id"
            }
        """.trimIndent()

            val exception = Assertions.assertThrows(IllegalArgumentException::class.java) {
                adapter.fromJson(json)
            }

            Assertions.assertEquals("Required property 'element' is missing", exception.message)
        }

    }

    @Nested
    @DisplayName("toJson")
    inner class ToJson {

        @Test
        fun `write to json`() {

            val lazyElement = LazyElement(
                id = "some-id",
                element = Text(
                    text = "Some Text",
                    textStyle = TextStyle(
                        textSize = 12,
                        isBold = true
                    ),
                )
            )
            val excepted = """
            {
              "id": "some-id",
              "element": {
                "type": "Text",
                "text": "Some Text",
                "textStyle": {
                  "textSize": 12,
                  "isBold": true
                }
              }
            }
        """.trimIndent()

            val actual = adapter.toJson(lazyElement)

            JSONAssert.assertEquals(excepted, actual, false)
        }

    }

}

