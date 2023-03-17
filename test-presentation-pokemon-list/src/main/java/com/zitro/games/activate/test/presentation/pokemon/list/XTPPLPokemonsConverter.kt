package com.zitro.games.activate.test.presentation.pokemon.list

import android.content.Context
import com.xcaret.test.domain.entity.XDTPokemonList
import com.xcaret.test.domain.usecase.XTDPokemonListUseCase
import com.zitro.games.activate.test.presentation.common.state.XTPCommonResultConverter
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class XTPPLPokemonsConverter @Inject constructor(@ApplicationContext private val context: Context) :
    XTPCommonResultConverter<XTDPokemonListUseCase.Response, XTPPLPokemonsModel>() {

    override fun convertSuccess(data: XTDPokemonListUseCase.Response): XTPPLPokemonsModel = XTPPLPokemonsModel(
            pokemons = XDTPokemonList(
                id = data.pokemonResponse.id,
                count = data.pokemonResponse.count,
                next = data.pokemonResponse.next,
                previous = data.pokemonResponse.previous,
                results = data.pokemonResponse.results,
        ))

}