package com.zitro.games.activate.test.presentation.pokemon.list

import com.zitro.games.activate.test.presentation.common.state.XTPCUiSingleEvent


sealed class XTPPLPokemonsUiSingleEvent: XTPCUiSingleEvent {
    data class CloseSession(val navRoute: String): XTPPLPokemonsUiSingleEvent()
}