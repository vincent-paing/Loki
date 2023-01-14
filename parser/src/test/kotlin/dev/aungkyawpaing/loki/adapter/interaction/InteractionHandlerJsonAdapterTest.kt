package dev.aungkyawpaing.loki.adapter.interaction

import com.squareup.moshi.JsonWriter
import dev.aungkyawpaing.loki.model.*
import dev.aungkyawpaing.loki.model.interaction.NavigationInteractionHandler
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.*

class InteractionHandlerJsonAdapterTest {

    val navigationInteractionHandlerJsonAdapter = mockk<NavigationInteractionHandlerJsonAdapter>(relaxed = true)
    val adapter =
        InteractionHandlerJsonAdapter(
            navigationInteractionHandlerJsonAdapter
        )

    val navigationInteractionHandler = NavigationInteractionHandler(
        page = "some-page"
    )

    @BeforeEach
    fun setUp() {
        every { navigationInteractionHandlerJsonAdapter.fromJsonValue(any()) } returns navigationInteractionHandler
    }

    @Nested
    @DisplayName("fromJson")
    inner class FromJson {
        @Test
        fun `returns Navigation given a navigation interaction element`() {
            val json = """
            {
              "type": "Navigation"
            }
        """.trimIndent()

            val actual = adapter.fromJson(json)

            Assertions.assertEquals(navigationInteractionHandler, actual)
        }

    }

    @Nested
    @DisplayName("toJson")
    inner class ToJson {

        @Test
        fun `write a navigation interaction element given Navigation`() {
            adapter.toJson(navigationInteractionHandler)

            verify {
                navigationInteractionHandlerJsonAdapter.toJson(any<JsonWriter>(), any())
            }
        }
    }
}