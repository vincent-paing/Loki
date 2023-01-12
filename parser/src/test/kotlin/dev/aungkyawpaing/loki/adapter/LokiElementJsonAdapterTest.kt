package dev.aungkyawpaing.loki.adapter

import com.squareup.moshi.JsonWriter
import dev.aungkyawpaing.loki.model.Text
import dev.aungkyawpaing.loki.model.metadata.TextStyle
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.*

class LokiElementJsonAdapterTest {

    val textJsonAdapter = mockk<TextJsonAdapter>(relaxed = true)
    val adapter = LokiElementJsonAdapter(textJsonAdapter)

    val text = Text(
        text = "Some Text",
        textStyle = TextStyle(
            textSize = 12,
            isBold = true,
        )
    )

    @BeforeEach
    fun setUp() {
        every { textJsonAdapter.fromJsonValue(any()) } returns text
    }

    @Nested
    @DisplayName("fromJson")
    inner class FromJson {
        @Test
        fun `reads Text given a text element`() {
            val json = """
            {
              "type": "text"
            }
        """.trimIndent()

            val actual = adapter.fromJson(json)

            Assertions.assertEquals(text, actual)
        }

    }

    @Nested
    @DisplayName("toJson")
    inner class ToJson {

        @Test
        fun `write a text element given Text`() {
            adapter.toJson(text)

            verify {
                textJsonAdapter.toJson(any<JsonWriter>(), any())
            }
        }
    }
}