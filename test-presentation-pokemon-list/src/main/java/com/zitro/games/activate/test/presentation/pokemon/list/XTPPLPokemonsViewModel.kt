package com.zitro.games.activate.test.presentation.pokemon.list

import androidx.lifecycle.viewModelScope
import com.xcaret.test.domain.entity.ZDTPokemonListRequest
import com.xcaret.test.domain.usecase.XTDPokemonListUseCase
import com.zitro.games.activate.test.presentation.common.state.XTPCMviViewModel
import com.zitro.games.activate.test.presentation.common.state.XTPCUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class XTPPLPokemonsViewModel @Inject constructor(
    private val xtdPokemonListUseCase: XTDPokemonListUseCase,
    private val xtpplPokemonsConverter: XTPPLPokemonsConverter
) : XTPCMviViewModel<XTPPLPokemonsModel, XTPCUiState<XTPPLPokemonsModel>, XTPPLPokemonsUiAction, XTPPLPokemonsUiSingleEvent>() {

    override fun initState(): XTPCUiState<XTPPLPokemonsModel> = XTPCUiState.Loading

    override fun handleAction(action: XTPPLPokemonsUiAction) {
        when(action){

            is XTPPLPokemonsUiAction.Load ->{
                submitState(XTPCUiState.Loading)
                loadList(ZDTPokemonListRequest(limit = 10, page = 0, list = listOf()))
            }

            is XTPPLPokemonsUiAction.NextLoad ->{
                submitState(XTPCUiState.Loading)
                loadList(action.request)
            }
        }
    }

    private fun loadList(request: ZDTPokemonListRequest){
        viewModelScope.launch {
            xtdPokemonListUseCase.execute(XTDPokemonListUseCase.Request(
                request
            )).map {
                xtpplPokemonsConverter.convert(it)
            }.collect {
                submitState(it)
            }
        }
    }
}