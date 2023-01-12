package dev.aungkyawpaing.loki.adapter.metadata

import dev.aungkyawpaing.loki.getJsonAdapter
import dev.aungkyawpaing.loki.model.metadata.ElementStyle
import dev.aungkyawpaing.loki.model.metadata.Length
import dev.aungkyawpaing.loki.model.metadata.Padding
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.skyscreamer.jsonassert.JSONAssert


class ElementStyleJsonAdapterTest {

    val adapter = getJsonAdapter<ElementStyle>()

    @Nested
    @DisplayName("fromJson")
    inner class FromJson {

        @Test
        fun `parse element style`() {
            val json = """
            {
              "width": 600,
              "height": 300,
              "padding": {
                "top": 12,
                "bottom": 12,
                "left": 12,
                "right": 12
              }
            }
        """.trimIndent()
            val expected = ElementStyle(
                width = Length.Number(600),
                height = Length.Number(300),
                padding = Padding(
                    top = 12,
                    bottom = 12,
                    left = 12,
                    right = 12
                )
            )

            val actual = adapter.fromJson(json)

            Assertions.assertEquals(expected, actual)
        }

        @Test
        fun `return value as nulls if it's empty object`() {
            val actual = adapter.fromJson("{}")

            Assertions.assertEquals(
                ElementStyle(
                    width = null,
                    height = null,
                    padding = null
                ), actual
            )
        }

        @Test
        fun `parse as null if it's null`() {
            val actual = adapter.fromJson("null")

            Assertions.assertNull(actual)
        }
    }

    @Nested
    @DisplayName("toJson")
    inner class ToJson {

        @Test
        fun `write to json`() {

            val elementStyle = ElementStyle(
                width = Length.Number(600),
                height = Length.Number(300),
                padding = Padding(
                    top = 12,
                    bottom = 12,
                    left = 12,
                    right = 12
                )
            )
            val expectedJson = """
            {
              "width": 600,
              "height": 300,
              "padding": {
                "top": 12,
                "bottom": 12,
                "left": 12,
                "right": 12
              }
            }
        """.trimIndent()

            val actual = adapter.toJson(elementStyle)

            JSONAssert.assertEquals(expectedJson, actual, false)
        }

    }
}