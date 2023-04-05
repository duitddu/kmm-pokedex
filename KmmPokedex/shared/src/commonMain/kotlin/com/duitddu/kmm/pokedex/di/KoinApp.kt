package com.duitddu.kmm.pokedex.di

import com.duitddu.kmm.pokedex.di.modules.apiModule
import com.duitddu.kmm.pokedex.di.modules.repositoryModule
import org.koin.dsl.koinApplication

fun createKoinApp() = koinApplication {
    modules(
        apiModule,
        repositoryModule
    )
}