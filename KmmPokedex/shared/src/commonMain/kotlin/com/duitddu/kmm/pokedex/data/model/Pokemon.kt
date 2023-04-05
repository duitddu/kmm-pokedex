package com.duitddu.kmm.pokedex.data.model

data class PokemonResult(
    val offset: Int,
    val pokemons: List<Pokemon>
)

data class Pokemon(
    val order: String,
    val name: String,
    val imageUrl: String
)