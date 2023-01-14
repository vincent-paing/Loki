package dev.aungkyawpaing.loki.adapter

import dev.aungkyawpaing.loki.getJsonAdapter
import dev.aungkyawpaing.loki.model.ButtonStyle
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Suppress("DANGEROUS_CHARACTERS")
class ButtonStyleJsonAdapterTest {

    private val adapter = getJsonAdapter<ButtonStyle>()

    @Nested
    @DisplayName("fromJson")
    inner class FromJson {

        @Test
        fun `parse 'filled'`() {
            val actual = adapter.fromJson("\"filled\"")

            Assertions.assertEquals(ButtonStyle.FILLED, actual)
        }

        @Test
        fun `parse 'outlined`() {
            val actual = adapter.fromJson("\"outlined\"")

            Assertions.assertEquals(ButtonStyle.OUTLINED, actual)
        }

        @Test
        fun `parse "text"`() {
            val actual = adapter.fromJson("\"text\"")

            Assertions.assertEquals(ButtonStyle.TEXT, actual)
        }

        @Test
        fun `throw error if it's neither of the above values`() {
            val exception = Assertions.assertThrows(IllegalArgumentException::class.java) {
                adapter.fromJson("\"random-value\"")
            }

            Assertions.assertEquals("Unknown value : random-value. It must either be 'filled','outlined' or 'text'", exception.message)
        }

    }

    @Nested
    @DisplayName("toJson")
    inner class ToJson {

        @Test
        fun `write FILLED`() {
            val actual = adapter.toJson(ButtonStyle.FILLED)

            Assertions.assertEquals("\"filled\"", actual)
        }

        @Test
        fun `write OUTLINED`() {
            val actual = adapter.toJson(ButtonStyle.OUTLINED)

            Assertions.assertEquals("\"outlined\"", actual)
        }

        @Test
        fun `write TEXT`() {
            val actual = adapter.toJson(ButtonStyle.TEXT)

            Assertions.assertEquals("\"text\"", actual)
        }
    }
}