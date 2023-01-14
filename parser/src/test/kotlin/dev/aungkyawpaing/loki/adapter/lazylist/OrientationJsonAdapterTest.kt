package dev.aungkyawpaing.loki.adapter.lazylist

import com.squareup.moshi.JsonDataException
import dev.aungkyawpaing.loki.getJsonAdapter
import dev.aungkyawpaing.loki.model.metadata.Orientation
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class OrientationJsonAdapterTest {

    private val adapter = getJsonAdapter<Orientation>()

    @Nested
    @DisplayName("fromJson")
    inner class FromJson {

        @Test
        fun `parse 'horizontal' as HORIZONTAL`() {
            val actual = adapter.fromJson("\"horizontal\"")

            Assertions.assertEquals(Orientation.HORIZONTAL, actual)
        }

        @Test
        fun `parse 'vertical' as VERTICAL`() {
            val actual = adapter.fromJson("\"vertical\"")

            Assertions.assertEquals(Orientation.VERTICAL, actual)
        }

        @Test
        fun `throw error if it is neither 'horizontal' or 'vertical'`() {
            val exception = Assertions.assertThrows(
                JsonDataException::class.java
            ) { adapter.fromJson("\"something-else\"") }


            Assertions.assertEquals(exception.message, "Orientation must either be 'vertical' or 'horizontal'")
        }
    }

    @Nested
    @DisplayName("toJson")
    inner class ToJson {

        @Test
        fun `write HORIZONTAL as 'horizontal'`() {
            val actual = adapter.toJson(Orientation.HORIZONTAL)

            Assertions.assertEquals("\"horizontal\"", actual)
        }

        @Test
        fun `write 'VERTICAL' as vertical`() {
            val actual = adapter.toJson(Orientation.VERTICAL)

            Assertions.assertEquals("\"vertical\"", actual)
        }

    }

}