package com.xcaret.test.data.local.injection

import com.xcaret.test.data.local.source.XTDLocalInfoLoginDataSourceImpl
import com.xcaret.test.data.local.source.XTDLocalSessionDataSourceImpl
import com.xcaret.test.data.local.source.XTDPokemonListLocalDataSourceImpl
import com.xcaret.test.data.repository.local.XTDRLocalLoginDataSource
import com.xcaret.test.data.repository.local.XTDRLocalPokemonListDataSource
import com.xcaret.test.data.repository.local.XTDRLocalSessionDataSource
import com.xcaret.test.data.repository.repository.XTDRLoginRepositoryImpl
import com.xcaret.test.data.repository.repository.XTDRPokemonListRepositoryImpl
import com.xcaret.test.domain.repository.XTDLoginRepository
import com.xcaret.test.domain.repository.XTDPokemonListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class XTDLocalDataSourceModule {

    @Binds
    abstract fun bindLoginDataSource(lostDataSourceImpl: XTDLocalInfoLoginDataSourceImpl): XTDRLocalLoginDataSource

    @Binds
    abstract fun bindLoginRepository(lostDataSourceImpl: XTDRLoginRepositoryImpl): XTDLoginRepository

    @Binds
    abstract fun bindPokemonListDataSource(lostDataSourceImpl: XTDPokemonListLocalDataSourceImpl): XTDRLocalPokemonListDataSource

    @Binds
    abstract fun bindPokemonListRepository(lostDataSourceImpl: XTDRPokemonListRepositoryImpl): XTDPokemonListRepository

    @Binds
    abstract fun bindSessionDataStore(interactionDataStore: XTDLocalSessionDataSourceImpl): XTDRLocalSessionDataSource
}