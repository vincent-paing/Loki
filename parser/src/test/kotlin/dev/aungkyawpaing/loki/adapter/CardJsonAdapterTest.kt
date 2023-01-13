package dev.aungkyawpaing.loki.adapter

import com.squareup.moshi.JsonDataException
import dev.aungkyawpaing.loki.getJsonAdapter
import dev.aungkyawpaing.loki.model.Text
import dev.aungkyawpaing.loki.model.metadata.ElementStyle
import dev.aungkyawpaing.loki.model.Card
import dev.aungkyawpaing.loki.model.metadata.TextStyle
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.skyscreamer.jsonassert.JSONAssert

class CardJsonAdapterTest {

    private val adapter = getJsonAdapter<Card>()

    @Nested
    @DisplayName("fromJson")
    inner class FromJson {
        @Test
        fun `parse card`() {
            val json = """
            {
              "type": "Card",
              "cornerRadius": 12,
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
            val expected = Card(
                cornerRadius = 12,
                children = listOf(
                    Text(
                        text = "Some Text",
                        textStyle = TextStyle(
                            textSize = 12,
                            isBold = true,
                        ),
                        style = null
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
              "type": "Card",
              "cornerRadius": 12,
              "children": [],
              "style": {}
            }
        """.trimIndent()
            val expected = Card(
                cornerRadius = 12,
                children = emptyList(),
                style = ElementStyle()
            )

            val actual = adapter.fromJson(json)

            Assertions.assertEquals(expected, actual)
        }

        @Test
        fun `set radius to 0 when cornerRadius is missing`() {
            val json = """
            {
              "type": "Card",
              "children": []
            }
        """.trimIndent()


            val actual = adapter.fromJson(json)?.cornerRadius

            Assertions.assertEquals(0, actual)
        }

        @Test
        fun `throws error when children is missing`() {
            val json = """
            {
              "type": "Card"
            }
        """.trimIndent()

            val exception = Assertions.assertThrows(
                JsonDataException::class.java
            ) { adapter.fromJson(json) }


            Assertions.assertEquals(exception.message, "Required property children is missing")
        }


    }

    @Nested
    @DisplayName("toJson")
    inner class ToJson {

        @Test
        fun `write to json`() {
            val card = Card(
                cornerRadius = 12,
                children = listOf(
                    Text(
                        text = "Some Text",
                        textStyle = TextStyle(
                            textSize = 12,
                            isBold = true,
                        ),
                        style = null
                    )
                )
            )
            val expected = """
            {
              "type": "Card",
              "cornerRadius": 12,
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

            val actual = adapter.toJson(card)

            JSONAssert.assertEquals(expected, actual, false)
        }
    }

}