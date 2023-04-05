package com.duitddu.kmm.pokedex.data.api.response

import kotlinx.serialization.Serializable

@Serializable
data class PokemonResultsResponse(
    val count: Int,
    val results: List<PokemonResponse>
)

@Serializable
data class PokemonResponse(
    val name: String,
    val url: String
)