package com.duitddu.kmm.pokedex.data.model

data class PokemonDetail(
    val order: String,
    val name: String,
    val imageUrl: String,
    val weight: Int,
    val height: Int,
    val types: List<PokemonType>
)

enum class PokemonType(val rawName: String, val color: Long) {
    NORMAL("normal", 0xFF000000)
}