package com.duitddu.kmm.pokedex.di.modules

import com.duitddu.kmm.pokedex.data.api.PokemonApi
import org.koin.dsl.module

val apiModule = module {
    single { PokemonApi() }
}