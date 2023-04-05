package com.duitddu.kmm.pokedex.di

import android.content.Context
import com.duitddu.kmm.pokedex.data.repository.PokemonRepository
import org.koin.dsl.module

class AndroidDependency(appContext: Context) {
    private val koinApp = createKoinApp().modules(
        module { single { appContext } }
    )

    fun getPokemonRepository(): PokemonRepository = koinApp.koin.get()
}