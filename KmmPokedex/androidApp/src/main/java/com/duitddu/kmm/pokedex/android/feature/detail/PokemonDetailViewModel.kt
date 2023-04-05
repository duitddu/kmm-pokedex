package com.duitddu.kmm.pokedex.android.feature.detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duitddu.kmm.pokedex.data.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _isLoading: MutableState<Boolean> = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _state: MutableState<PokemonDetailState> = mutableStateOf(PokemonDetailState.Idle)
    val state: State<PokemonDetailState> = _state

    init {
        loadPokemon()
    }

    private fun loadPokemon() {
        savedStateHandle.get<String>("name")?.let {
            loadPokemon(it)
        } ?: {
            _state.value = PokemonDetailState.ArgumentError
        }
    }

    private fun loadPokemon(name: String) {
        viewModelScope.launch(Dispatchers.Main) {
            _isLoading.value = true
            runCatching {
                withContext(Dispatchers.IO) {
                    pokemonRepository.getPokemonDetail(name)
                }
            }.onSuccess {
                _state.value = PokemonDetailState.Success(it)
                _isLoading.value = false
            }.onFailure {
                _state.value = PokemonDetailState.Failure
                _isLoading.value = false
            }
        }
    }
}