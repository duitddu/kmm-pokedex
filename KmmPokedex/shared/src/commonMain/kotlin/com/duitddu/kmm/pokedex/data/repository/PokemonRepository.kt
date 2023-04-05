package com.duitddu.kmm.pokedex.data.repository

import com.duitddu.kmm.pokedex.data.api.PokemonApi
import com.duitddu.kmm.pokedex.data.api.response.PokemonDetailResponse
import com.duitddu.kmm.pokedex.data.api.response.PokemonResponse
import com.duitddu.kmm.pokedex.data.api.response.PokemonResultsResponse
import com.duitddu.kmm.pokedex.data.model.Pokemon
import com.duitddu.kmm.pokedex.data.model.PokemonDetail
import com.duitddu.kmm.pokedex.data.model.PokemonResult
import com.duitddu.kmm.pokedex.data.model.PokemonType

class PokemonRepository(
    private val pokemonApi: PokemonApi
) {
    suspend fun getPokemonResults(
        limit: Int = 100,
        offset: Int
    ): PokemonResult = pokemonApi.getPokemonResults(limit, offset).asModel(offset)

    suspend fun getPokemonDetail(
        name: String
    ): PokemonDetail = pokemonApi.getPokemonDetail(name).asModel()

    private fun PokemonResultsResponse.asModel(
        offset: Int
    ): PokemonResult =
        PokemonResult(
            offset = offset + results.size,
            pokemons = results.map { it.asModel() }
        )

    private fun PokemonResponse.asModel(): Pokemon {
        val order = url.fetchPokemonOrder()
        return Pokemon(
            order = order,
            name = name,
            imageUrl = order.toPokemonImageUrl()
        )
    }

    private fun PokemonDetailResponse.asModel(): PokemonDetail {
        val order = order.toString().toPokemonOrderFormat()

        return PokemonDetail(
            order = order,
            name = this.name,
            imageUrl = order.toPokemonImageUrl(),
            weight = this.weight,
            height = this.height,
            types = this.types.map { typeInfo ->
                PokemonType.values().find { it.name.equals(typeInfo.type.name, ignoreCase = true) } ?: PokemonType.NORMAL
            }
        )
    }

    private fun String.fetchPokemonOrder(): String =
        trimEnd('/').split("/").last().toPokemonOrderFormat()

    private fun String.toPokemonOrderFormat(): String =
        padStart(3, '0')

    private fun String.toPokemonImageUrl(): String =
        "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/$this.png"
}