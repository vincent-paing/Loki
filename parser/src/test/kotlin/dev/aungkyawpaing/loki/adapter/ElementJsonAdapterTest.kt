package dev.aungkyawpaing.loki.adapter

import com.squareup.moshi.JsonWriter
import dev.aungkyawpaing.loki.model.*
import dev.aungkyawpaing.loki.model.metadata.TextStyle
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.*

class ElementJsonAdapterTest {

    val textJsonAdapter = mockk<TextJsonAdapter>(relaxed = true)
    val imageJsonAdapter = mockk<ImageJsonAdapter>(relaxed = true)
    val rowJsonAdapter = mockk<RowJsonAdapter>(relaxed = true)
    val columnJsonAdapter = mockk<ColumnJsonAdapter>(relaxed = true)
    val cardJsonAdapter = mockk<CardJsonAdapter>(relaxed = true)
    val adapter =
        ElementJsonAdapter(textJsonAdapter, imageJsonAdapter, rowJsonAdapter, columnJsonAdapter, cardJsonAdapter)

    val text = Text(
        text = "", textStyle = TextStyle(textSize = 0, isBold = false), style = null
    )
    val image = Image(
        url = "", altText = null, style = null
    )
    val row = Row(
        children = emptyList(),
    )
    val column = Column(
        children = emptyList(),
    )
    val card = Card(
        children = emptyList(),
    )

    @BeforeEach
    fun setUp() {
        every { textJsonAdapter.fromJsonValue(any()) } returns text
        every { imageJsonAdapter.fromJsonValue(any()) } returns image
        every { rowJsonAdapter.fromJsonValue(any()) } returns row
        every { columnJsonAdapter.fromJsonValue(any()) } returns column
        every { cardJsonAdapter.fromJsonValue(any()) } returns card
    }

    @Nested
    @DisplayName("fromJson")
    inner class FromJson {
        @Test
        fun `reads Text given a text element`() {
            val json = """
            {
              "type": "Text"
            }
        """.trimIndent()

            val actual = adapter.fromJson(json)

            Assertions.assertEquals(text, actual)
        }

        @Test
        fun `reads Image given an image element`() {
            val json = """
            {
              "type": "Image"
            }
        """.trimIndent()

            val actual = adapter.fromJson(json)

            Assertions.assertEquals(image, actual)
        }

        @Test
        fun `reads Row given an row element`() {
            val json = """
            {
              "type": "Row"
            }
        """.trimIndent()

            val actual = adapter.fromJson(json)

            Assertions.assertEquals(row, actual)
        }

        @Test
        fun `reads Column given an column element`() {
            val json = """
            {
              "type": "Column"
            }
        """.trimIndent()

            val actual = adapter.fromJson(json)

            Assertions.assertEquals(column, actual)
        }

        @Test
        fun `reads Card given a card element`() {
            val json = """
            {
              "type": "Card"
            }
        """.trimIndent()

            val actual = adapter.fromJson(json)

            Assertions.assertEquals(card, actual)
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

        @Test
        fun `write an image element given Image`() {
            adapter.toJson(image)

            verify {
                imageJsonAdapter.toJson(any<JsonWriter>(), any())
            }
        }

        @Test
        fun `write an row element given Row`() {
            adapter.toJson(row)

            verify {
                rowJsonAdapter.toJson(any<JsonWriter>(), any())
            }
        }

        @Test
        fun `write a column element given Column`() {
            adapter.toJson(column)

            verify {
                columnJsonAdapter.toJson(any<JsonWriter>(), any())
            }
        }

        @Test
        fun `write a card element given Card`() {
            adapter.toJson(card)

            verify {
                cardJsonAdapter.toJson(any<JsonWriter>(), any())
            }
        }
    }
}