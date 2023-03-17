package com.xcaret.test.data.remote.source

import android.annotation.SuppressLint
import com.xcaret.test.data.remote.networking.XTDRPokemonListApiModel
import com.xcaret.test.data.remote.networking.XTDRService
import com.xcaret.test.data.repository.remote.XTDRemotePokemonListDataSource
import com.xcaret.test.domain.entity.XDTPokemonList
import com.xcaret.test.domain.entity.XTDRPokemon
import com.xcaret.test.domain.entity.XTDUseCaseException
import com.xcaret.test.domain.entity.ZDTPokemonListRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class XTDRemotePokemonListDataSourceImpl @Inject constructor(private val service: XTDRService) :
    XTDRemotePokemonListDataSource {
    override fun getPokemon(request: ZDTPokemonListRequest): Flow<XDTPokemonList> = flow {
        this.emit(service.listPokemon(request.limit, request.page))
    }.map { data ->
        convert(data, request)
    }.catch {
        throw XTDUseCaseException.XTDPokemonListException(it)
    }

    @SuppressLint("SuspiciousIndentation")
    private fun convert(pokemonList: XTDRPokemonListApiModel, request: ZDTPokemonListRequest): XDTPokemonList {
        val pokemons = request.list.toMutableList()
        val newPokemon = mutableSetOf<XTDRPokemon>()

        pokemonList.results?.forEach {
            pokemons.add(
                XTDRPokemon(
                    name = it.name,
                    url = it.url
                )
            )
        }

        pokemons.forEach { pokemon -> newPokemon.add(pokemon) }

        return XDTPokemonList(
            id = request.page,
            count = pokemonList.count,
            next = pokemonList.next,
            previous = pokemonList.previous,
            results = newPokemon.toList(),
        )
    }

}