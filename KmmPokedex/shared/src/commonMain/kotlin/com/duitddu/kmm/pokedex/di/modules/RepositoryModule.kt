package com.duitddu.kmm.pokedex.di.modules

import com.duitddu.kmm.pokedex.data.repository.PokemonRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { PokemonRepository(get()) }
}