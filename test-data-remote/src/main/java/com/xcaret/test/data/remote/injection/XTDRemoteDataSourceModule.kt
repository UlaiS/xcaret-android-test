package com.xcaret.test.data.remote.injection

import com.xcaret.test.data.remote.source.XTDRemoteLoginDataSourceImpl
import com.xcaret.test.data.remote.source.XTDRemotePokemonListDataSourceImpl
import com.xcaret.test.data.repository.remote.XTDRemoteLoginDataSource
import com.xcaret.test.data.repository.remote.XTDRemotePokemonListDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class XTDRemoteDataSourceModule {

    @Binds
    abstract fun bindLoginDataSource(loginDataSourceImpl: XTDRemoteLoginDataSourceImpl): XTDRemoteLoginDataSource

    @Binds
    abstract fun bindPokemonListDataSource(pokemonDataSourceImpl: XTDRemotePokemonListDataSourceImpl): XTDRemotePokemonListDataSource
}