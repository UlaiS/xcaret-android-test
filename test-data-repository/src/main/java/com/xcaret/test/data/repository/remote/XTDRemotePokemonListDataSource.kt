package com.xcaret.test.data.repository.remote

import com.xcaret.test.domain.entity.XDTPokemonList
import com.xcaret.test.domain.entity.ZDTPokemonListRequest
import kotlinx.coroutines.flow.Flow

interface XTDRemotePokemonListDataSource {
    fun getPokemon(request: ZDTPokemonListRequest): Flow<XDTPokemonList>
}