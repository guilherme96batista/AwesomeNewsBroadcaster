package pt.guilhermerodrigues.awesomenewsbrodcaster.data.models

import kotlinx.serialization.Serializable

@Serializable
data class SourceDto(
    val id: String? = null,
    val name: String? = null
)