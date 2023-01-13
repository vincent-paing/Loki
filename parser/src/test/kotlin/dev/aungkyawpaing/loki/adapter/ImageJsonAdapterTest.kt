package dev.aungkyawpaing.loki.adapter

import com.squareup.moshi.JsonDataException
import dev.aungkyawpaing.loki.getJsonAdapter
import dev.aungkyawpaing.loki.model.Image
import dev.aungkyawpaing.loki.model.metadata.ElementStyle
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.skyscreamer.jsonassert.JSONAssert

class ImageJsonAdapterTest {

    val adapter = getJsonAdapter<Image>()

    @Nested
    @DisplayName("fromJson")
    inner class FromJson {

        @Test
        fun `parse image`() {
            val json = """
            {
              "type": "Image",
              "url": "https://some-image.com",
              "altText": "some-text"
            }
        """.trimIndent()
            val expected = Image(
                url = "https://some-image.com", altText = "some-text", style = null
            )

            val actual = adapter.fromJson(json)

            Assertions.assertEquals(expected, actual)
        }

        @Test
        fun `parse style if it exists`() {
            val json = """
            {
              "type": "Image",
              "url": "https://some-image.com",
              "altText": "some-text",
              "style": {}
            }
        """.trimIndent()
            val expected = Image(
                url = "https://some-image.com", altText = "some-text", style = ElementStyle()
            )

            val actual = adapter.fromJson(json)

            Assertions.assertEquals(expected, actual)
        }

        @Test
        fun `throws error when url is missing`() {
            val json = """
            {
              "type": "Image",
              "altText": "some-text"
            }
        """.trimIndent()

            val exception = Assertions.assertThrows(
                JsonDataException::class.java
            ) { adapter.fromJson(json) }


            Assertions.assertEquals(exception.message, "Required property url is missing")
        }

        @Test
        fun `allows altText to be optional`() {
            val json = """
            {
              "type": "Image",
              "url": "https://some-image.com"
            }
        """.trimIndent()

            val expected = Image(
                url = "https://some-image.com", altText = null, style = null
            )

            val actual = adapter.fromJson(json)

            Assertions.assertEquals(expected, actual)
        }

    }

    @Nested
    @DisplayName("toJson")
    inner class ToJson {

        @Test
        fun `write to json`() {
            val image = Image(
                url = "https://some-image.com", altText = "some-text", style = null
            )

            val actual = adapter.toJson(image)
            val expected = """
            {
              "type": "Image",
              "url": "https://some-image.com",
              "altText": "some-text"
            }
        """.trimIndent()

            JSONAssert.assertEquals(expected, actual, false)
        }

    }


}