package dev.aungkyawpaing.loki.model.metadata

sealed class Length {

    data class Number(val value: Int) : Length()

    object Max : Length()
}