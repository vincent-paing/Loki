package dev.aungkyawpaing.loki.adapter

import dev.aungkyawpaing.loki.getJsonAdapter
import dev.aungkyawpaing.loki.model.Text
import dev.aungkyawpaing.loki.model.metadata.ElementStyle
import dev.aungkyawpaing.loki.model.Row
import dev.aungkyawpaing.loki.model.metadata.TextStyle
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.skyscreamer.jsonassert.JSONAssert

class RowJsonAdapterTest {

    private val adapter = getJsonAdapter<Row>()

    @Nested
    @DisplayName("fromJson")
    inner class FromJson {
        @Test
        fun `parse row`() {
            val json = """
            {
              "type": "Row",
              "children": [{
                "type": "Text",
                "text": "Some Text",
                "textStyle": {
                  "textSize": 12,
                  "isBold": true
                }
              },
              {
                "type": "Text",
                "text": "Some Text 2",
                "textStyle": {
                  "textSize": 12,
                  "isBold": true
                }
              }]
            }
        """.trimIndent()
            val expected = Row(
                children = listOf(
                    Text(
                        text = "Some Text", textStyle = TextStyle(
                            textSize = 12,
                            isBold = true,
                        ), style = null
                    ),
                    Text(
                        text = "Some Text 2",
                        textStyle = TextStyle(
                            textSize = 12,
                            isBold = true,
                        )
                    )
                )
            )

            val actual = adapter.fromJson(json)

            Assertions.assertEquals(expected, actual)
        }

        @Test
        fun `parse style if it exists`() {
            val json = """
            {
              "type": "Row",
              "children": [],
              "style": {}
            }
        """.trimIndent()
            val expected = Row(
                children = emptyList(), style = ElementStyle()
            )

            val actual = adapter.fromJson(json)

            Assertions.assertEquals(expected, actual)
        }

        @Test
        fun `throws error when children is missing`() {
            val json = """
            {
              "type": "Row"
            }
        """.trimIndent()

            val exception = Assertions.assertThrows(
                IllegalArgumentException::class.java
            ) { adapter.fromJson(json) }


            Assertions.assertEquals("Required property children is missing", exception.message)
        }


    }

    @Nested
    @DisplayName("toJson")
    inner class ToJson {

        @Test
        fun `write to json`() {
            val row = Row(
                children = listOf(
                    Text(
                        text = "Some Text", textStyle = TextStyle(
                            textSize = 12,
                            isBold = true,
                        ), style = null
                    )
                )
            )
            val expected = """
            {
              "type": "Row",
              "children": [{
                "type": "Text",
                "text": "Some Text",
                "textStyle": {
                  "textSize": 12,
                  "isBold": true
                }
              }]
            }
        """.trimIndent()

            val actual = adapter.toJson(row)

            JSONAssert.assertEquals(expected, actual, false)
        }
    }

}