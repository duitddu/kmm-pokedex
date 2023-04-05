package com.duitddu.kmm.pokedex.android.feature.detail

import com.duitddu.kmm.pokedex.data.model.PokemonDetail

sealed class PokemonDetailState {
    object Idle : PokemonDetailState()

    data class Success(
        val pokemon: PokemonDetail
    ) : PokemonDetailState()

    object ArgumentError : PokemonDetailState()

    object Failure: PokemonDetailState()
}