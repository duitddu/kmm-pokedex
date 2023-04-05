package com.duitddu.kmm.pokedex.android.feature.list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duitddu.kmm.pokedex.data.model.Pokemon
import com.duitddu.kmm.pokedex.data.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
): ViewModel() {
    private val _isLoading: MutableState<Boolean> = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _pokemonList: MutableState<List<Pokemon>> = mutableStateOf(emptyList())
    val pokemonList: State<List<Pokemon>> = _pokemonList

    private var pokemonOffset: Int = 0

    fun loadPokemonList() {
        if (_isLoading.value) return

        viewModelScope.launch(Dispatchers.Main) {
            _isLoading.value = true
            runCatching {
                withContext(Dispatchers.IO) {
                    pokemonRepository.getPokemonResults(offset = pokemonOffset)
                }
            }.onSuccess {
                _isLoading.value = false
                _pokemonList.value = _pokemonList.value + it.pokemons
                pokemonOffset = it.offset
            }.onFailure {
                _isLoading.value = false
            }
        }
    }
}