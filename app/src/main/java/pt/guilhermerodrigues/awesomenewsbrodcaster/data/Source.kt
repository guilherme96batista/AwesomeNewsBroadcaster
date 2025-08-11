package pt.guilhermerodrigues.awesomenewsbrodcaster.data

import kotlinx.serialization.Serializable

@Serializable
data class Source (
    val id: String,
    val name: String
)