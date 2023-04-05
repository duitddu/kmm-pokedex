package com.duitddu.kmm.pokedex.android.di

import com.duitddu.kmm.pokedex.data.repository.PokemonRepository
import com.duitddu.kmm.pokedex.di.AndroidDependency
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providePokemonRepository(
        androidDependency: AndroidDependency
    ): PokemonRepository = androidDependency.getPokemonRepository()
}