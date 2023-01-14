package dev.aungkyawpaing.loki.adapter.lazylist

import dev.aungkyawpaing.loki.getJsonAdapter
import dev.aungkyawpaing.loki.model.LazyElement
import dev.aungkyawpaing.loki.model.LazyList
import dev.aungkyawpaing.loki.model.Text
import dev.aungkyawpaing.loki.model.metadata.ElementStyle
import dev.aungkyawpaing.loki.model.metadata.Orientation
import dev.aungkyawpaing.loki.model.metadata.TextStyle
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.skyscreamer.jsonassert.JSONAssert
import java.lang.IllegalArgumentException

class LazyListJsonAdapterTest {

    val adapter = getJsonAdapter<LazyList>()

    @Nested
    @DisplayName("fromJson")
    inner class FromJson {

        @Test
        fun `parse lazy list`() {
            val json = """
            {
              "type": "LazyList",
              "orientation": "horizontal",
              "children": [{
                "id": "some-id",
                "element": {
                  "type": "Text",
                  "text": "Some Text",
                  "textStyle": {
                    "textSize": 12,
                    "isBold": true
                  }
                }
              }]
            }
        """.trimIndent()

            val expected = LazyList(
                orientation = Orientation.HORIZONTAL,
                children = listOf(
                    LazyElement(
                        id = "some-id",
                        element = Text(
                            text = "Some Text",
                            textStyle = TextStyle(
                                textSize = 12,
                                isBold = true
                            ),
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
              "type": "LazyList",
              "orientation": "horizontal",
              "children": [{
                "id": "some-id",
                "element": {
                  "type": "Text",
                  "text": "Some Text",
                  "textStyle": {
                    "textSize": 12,
                    "isBold": true
                  }
                }
              }],
              "style": {}
            }
        """.trimIndent()

            val expected = LazyList(
                orientation = Orientation.HORIZONTAL,
                children = listOf(
                    LazyElement(
                        id = "some-id",
                        element = Text(
                            text = "Some Text",
                            textStyle = TextStyle(
                                textSize = 12,
                                isBold = true
                            ),
                        )
                    )
                ),
                style = ElementStyle()
            )

            val actual = adapter.fromJson(json)

            Assertions.assertEquals(expected, actual)
        }

        @Test
        fun `throw error when orientation is missing`() {
            val json = """
            {
              "type": "LazyList",
              "children": [{
                "id": "some-id",
                "element": {
                  "type": "Text",
                  "text": "Some Text",
                  "textStyle": {
                    "textSize": 12,
                    "isBold": true
                  }
                }
              }]
            }
        """.trimIndent()

            val exception = Assertions.assertThrows(IllegalArgumentException::class.java) {
                adapter.fromJson(json)
            }
            Assertions.assertEquals("Required property 'orientation' is missing", exception.message)
        }

        @Test
        fun `have empty list when children is missing`() {
            val json = """
            {
              "type": "LazyList",
              "orientation": "horizontal"
            }
        """.trimIndent()

            val actual = adapter.fromJson(json)?.children
            Assertions.assertEquals(emptyList<LazyElement>(), actual)
        }
    }

    @Nested
    @DisplayName("toJson")
    inner class ToJson {

        @Test
        fun `write to json`() {


            val lazyList = LazyList(
                orientation = Orientation.HORIZONTAL,
                children = listOf(
                    LazyElement(
                        id = "some-id",
                        element = Text(
                            text = "Some Text",
                            textStyle = TextStyle(
                                textSize = 12,
                                isBold = true
                            ),
                        )
                    )
                )
            )
            val excepted = """
            {
              "type": "LazyList",
              "orientation": "horizontal",
              "children": [{
                "id": "some-id",
                "element": {
                  "type": "Text",
                  "text": "Some Text",
                  "textStyle": {
                    "textSize": 12,
                    "isBold": true
                  }
                }
              }]
            }
        """.trimIndent()

            val actual = adapter.toJson(lazyList)

            JSONAssert.assertEquals(excepted, actual, false)
        }

    }

}

