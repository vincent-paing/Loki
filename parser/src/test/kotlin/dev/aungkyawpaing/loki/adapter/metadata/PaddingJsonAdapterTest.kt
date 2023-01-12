package dev.aungkyawpaing.loki.adapter.metadata

import dev.aungkyawpaing.loki.getJsonAdapter
import dev.aungkyawpaing.loki.model.metadata.Padding
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.skyscreamer.jsonassert.JSONAssert


class PaddingJsonAdapterTest {

    val adapter = getJsonAdapter<Padding>()

    @Nested
    @DisplayName("fromJson")
    inner class FromJson {

        @Test
        fun `parse padding`() {
            val json = """
            {
              "top": 12,
              "bottom": 12,
              "left": 12,
              "right": 12
            }
        """.trimIndent()
            val expected = Padding(
                top = 12,
                bottom = 12,
                left = 12,
                right = 12
            )

            val actual = adapter.fromJson(json)

            Assertions.assertEquals(expected, actual)
        }

        @Test
        fun `parse top as zero if it doesn't exist`() {
            val json = """
            {
              "bottom": 12,
              "left": 12,
              "right": 12
            }
        """.trimIndent()

            val actual = adapter.fromJson(json)

            Assertions.assertEquals(0, actual!!.top)
        }

        @Test
        fun `parse bottom as zero if it doesn't exist`() {
            val json = """
            {
              "top": 12,
              "left": 12,
              "right": 12
            }
        """.trimIndent()

            val actual = adapter.fromJson(json)

            Assertions.assertEquals(0, actual!!.bottom)
        }

        @Test
        fun `parse left as zero if it doesn't exist`() {
            val json = """
            {
              "top": 12,
              "bottom": 12,
              "right": 12
            }
        """.trimIndent()

            val actual = adapter.fromJson(json)

            Assertions.assertEquals(0, actual!!.left)
        }

        @Test
        fun `parse right as zero if it doesn't exist`() {
            val json = """
            {
              "top": 12,
              "bottom": 12,
              "left": 12
            }
        """.trimIndent()

            val actual = adapter.fromJson(json)

            Assertions.assertEquals(0, actual!!.right)
        }

        @Test
        fun `return null given null`() {
            val actual = adapter.fromJson("null")

            Assertions.assertNull(actual)
        }
    }

    @Nested
    @DisplayName("toJson")
    inner class ToJson {

        @Test
        fun `write to json`() {

            val padding = Padding(
                top = 12,
                bottom = 12,
                left = 12,
                right = 12
            )

            val expectedJson = """
            {
              "top": 12,
              "bottom": 12,
              "left": 12,
              "right": 12
            }
        """.trimIndent()

            val actual = adapter.toJson(padding)

            JSONAssert.assertEquals(expectedJson, actual, false)
        }

    }
}