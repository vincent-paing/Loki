package dev.aungkyawpaing.loki.model.interaction

enum class InteractionHandlerType(val typeString: String) {
    NAVIGATION("Navigation");

    companion object {
        fun fromTypeString(typeString: String?) = values().find { it.typeString == typeString }
    }
}