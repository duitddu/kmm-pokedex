package com.duitddu.kmm.pokedex.data.api.response

import kotlinx.serialization.Serializable

@Serializable
data class PokemonDetailResponse(
    val id: Int,
    val name: String,
    val order: Int,
    val type: PokemonTypeResponse,
    val height: Int,
    val weight: Int
)

@Serializable
data class PokemonTypeResponse(
    val slot: Int,
    val type: List<PokemonTypeInfoResponse>
)

@Serializable
data class PokemonTypeInfoResponse(
    val name: String,
    val url: String
)