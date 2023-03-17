package com.xcaret.test.data.repository.repository

import com.xcaret.test.data.repository.local.XTDRLocalPokemonListDataSource
import com.xcaret.test.data.repository.remote.XTDRemotePokemonListDataSource
import com.xcaret.test.domain.entity.XDTPokemonList
import com.xcaret.test.domain.entity.ZDTPokemonListRequest
import com.xcaret.test.domain.repository.XTDPokemonListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class XTDRPokemonListRepositoryImpl @Inject constructor(
    private val localPokemonListDataSource: XTDRLocalPokemonListDataSource,
    private val remotePokemonListDataSource: XTDRemotePokemonListDataSource
): XTDPokemonListRepository {

    override fun pokemonList(request: ZDTPokemonListRequest): Flow<XDTPokemonList> = remotePokemonListDataSource.getPokemon(request).onEach {
        localPokemonListDataSource.setPokemon(it)
    }

    override fun pokemonLocalList(request: ZDTPokemonListRequest): Flow<XDTPokemonList?> = localPokemonListDataSource.getLocalPokemon(request).onEach {

    }
}