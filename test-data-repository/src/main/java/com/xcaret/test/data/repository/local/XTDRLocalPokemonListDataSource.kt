package com.xcaret.test.data.repository.local

import com.xcaret.test.domain.entity.XDTPokemonList
import com.xcaret.test.domain.entity.ZDTPokemonListRequest
import kotlinx.coroutines.flow.Flow

interface XTDRLocalPokemonListDataSource {
    fun getPokemon(request: ZDTPokemonListRequest): Flow<XDTPokemonList>
    fun getLocalPokemon(request: ZDTPokemonListRequest): Flow<XDTPokemonList?>
    suspend fun setPokemon(pokemonsList: XDTPokemonList)
}