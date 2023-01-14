package dev.aungkyawpaing.loki.model.interaction

data class NavigationInteractionHandler(
    val page: String
) : InteractionHandler(InteractionHandlerType.NAVIGATION)