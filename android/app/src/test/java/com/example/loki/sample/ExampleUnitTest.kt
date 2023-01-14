package com.example.loki.sample

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import dev.aungkyawpaing.loki.LokiJsonAdapterFactory
import dev.aungkyawpaing.loki.model.Text
import dev.aungkyawpaing.loki.model.metadata.TextStyle
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    val adapter = getJsonAdapter<Text>()


    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun `parse text correctly`() {
        val json = """
            {
              "type": "Text",
              "text": "Some Text",
              "textStyle": {
                "textSize": 12,
                "isBold": true
              }
            }
        """.trimIndent()
        val expected = Text(
            text = "Some Text",
            textStyle = TextStyle(
                textSize = 12,
                isBold = true,
            ),
            style = null
        )

        val actual = adapter.fromJson(json)

        assertEquals(expected, actual)

    }
}

inline fun <reified T> getJsonAdapter(): JsonAdapter<T> {
    return Moshi.Builder().add(LokiJsonAdapterFactory()).build().adapter(T::class.java)
}