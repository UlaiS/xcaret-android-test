package com.xcaret.test.data.local.source

import com.xcaret.test.data.local.db.XTDPokemonListDao
import com.xcaret.test.data.local.entity.XDTLPokemonListEntity
import com.xcaret.test.data.local.entity.XTDRPokemonEntity
import com.xcaret.test.data.repository.local.XTDRLocalPokemonListDataSource
import com.xcaret.test.domain.entity.XDTPokemonList
import com.xcaret.test.domain.entity.XTDRPokemon
import com.xcaret.test.domain.entity.ZDTPokemonListRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class XTDPokemonListLocalDataSourceImpl @Inject constructor(private val  xtdPokemonListDao: XTDPokemonListDao) :
    XTDRLocalPokemonListDataSource {

    override fun getPokemon(request: ZDTPokemonListRequest): Flow<XDTPokemonList> = xtdPokemonListDao
        .getPokemonList().map { pokemonList ->
            converter(pokemonList, request.list)
        }

    override fun getLocalPokemon(request: ZDTPokemonListRequest): Flow<XDTPokemonList> = xtdPokemonListDao
        .getPokemonList(request.page).map {pokemonList ->
            converter(pokemonList, request.list)
        }

    override suspend fun setPokemon(pokemonsList: XDTPokemonList) {
        xtdPokemonListDao.insertPokemon(converter(pokemonsList))
    }

    private fun converter(pokemonList: XDTLPokemonListEntity, listAdding: List<XTDRPokemon>): XDTPokemonList {
        val pokemons = listAdding.toMutableList()
        val newPokemon = mutableSetOf<XTDRPokemon>()

        pokemonList.results.forEach {
            pokemons.add(
                XTDRPokemon(
                    name = it.name,
                    url = it.url
                )
            )
        }

        pokemons.forEach { pokemon -> newPokemon.add(pokemon) }

        return XDTPokemonList(
            id = pokemonList.id,
            count = pokemonList.count,
            next = pokemonList.next,
            previous = pokemonList.previous,
            results = newPokemon.toList(),
        )
    }

    private fun converter(pokemonList: XDTPokemonList): XDTLPokemonListEntity {
        val pokemons = mutableListOf<XTDRPokemonEntity>()

        pokemonList.results.forEach {
            pokemons.add(
                XTDRPokemonEntity(
                    name = it.name,
                    url = it.url
                )

            )
        }
        return XDTLPokemonListEntity(
            id = pokemonList.id,
            count = pokemonList.count,
            next = pokemonList.next,
            previous = pokemonList.previous,
            results = pokemons,
        )
    }

}