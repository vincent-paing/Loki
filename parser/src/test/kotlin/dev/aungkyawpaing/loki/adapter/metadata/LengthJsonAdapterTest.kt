package dev.aungkyawpaing.loki.adapter.metadata

import dev.aungkyawpaing.loki.getJsonAdapter
import dev.aungkyawpaing.loki.model.metadata.Length
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.lang.IllegalArgumentException

@Suppress("DANGEROUS_CHARACTERS")
class LengthJsonAdapterTest {

    private val adapter = getJsonAdapter<Length>()

    @Nested
    @DisplayName("fromJson")
    inner class FromJson {

        @Test
        fun `parse number given a number`() {
            val value = 400
            val actual = adapter.fromJson("$value")

            Assertions.assertEquals(Length.Number(value), actual)
        }

        @Test
        fun `parse number given a decimal number`() {
            val value = 400.0
            val actual = adapter.fromJson("$value")

            Assertions.assertEquals(Length.Number(400), actual)
        }

        @Test
        fun `parse "max" given max`() {
            val actual = adapter.fromJson("\"max\"")

            Assertions.assertEquals(Length.Max, actual)
        }

        @Test
        fun `throw error if it's neither decimal number or max`() {
            val exception = Assertions.assertThrows(IllegalArgumentException::class.java) {
                adapter.fromJson("\"random-value\"")
            }

            Assertions.assertEquals("Unknown length : random-value. It must either be numeral or 'max'", exception.message)
        }

        @Test
        fun `returns null if it's null`() {
            val actual = adapter.fromJson("null")

            Assertions.assertNull(actual)
        }

    }

    @Nested
    @DisplayName("toJson")
    inner class ToJson {

        @Test
        fun `write number if it's number value`() {
            val value = 400
            val actual = adapter.toJson(Length.Number(400))

            Assertions.assertEquals("$value", actual)
        }

        @Test
        fun `write max if it's max`() {
            val actual = adapter.toJson(Length.Max)

            Assertions.assertEquals("\"max\"", actual)
        }

        @Test
        fun `returns null if it's null`() {
            val actual = adapter.toJson(null)

            Assertions.assertEquals("null", actual)
        }

    }
}