package com.duitddu.kmm.pokedex.android.di

import android.content.Context
import com.duitddu.kmm.pokedex.di.AndroidDependency
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AndroidDependencyModule {

    @Provides
    @Singleton
    fun provideAndroidDependency(
        @ApplicationContext context: Context
    ): AndroidDependency = AndroidDependency(context)
}