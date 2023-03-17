package com.zitro.games.activate.test.presentation.pokemon.list

import com.xcaret.test.domain.entity.ZDTPokemonListRequest
import com.zitro.games.activate.test.presentation.common.state.XTPCUiAction

sealed class XTPPLPokemonsUiAction: XTPCUiAction {
    object Load : XTPPLPokemonsUiAction()
    data class NextLoad(val request: ZDTPokemonListRequest) : XTPPLPokemonsUiAction()
}