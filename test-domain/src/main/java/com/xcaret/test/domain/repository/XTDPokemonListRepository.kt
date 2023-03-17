package com.xcaret.test.domain.repository

import com.xcaret.test.domain.entity.XDTPokemonList
import com.xcaret.test.domain.entity.ZDTPokemonListRequest
import kotlinx.coroutines.flow.Flow

interface XTDPokemonListRepository {
    fun pokemonList(request: ZDTPokemonListRequest): Flow<XDTPokemonList>
    fun pokemonLocalList(request: ZDTPokemonListRequest): Flow<XDTPokemonList?>
}