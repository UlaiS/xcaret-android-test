package com.zitro.games.activate.test.presentation.login

import androidx.lifecycle.viewModelScope
import com.xcaret.test.domain.entity.XTDSession
import com.xcaret.test.domain.usecase.XTDLoginUseCase
import com.xcaret.test.domain.usecase.XTDSessionUseCase
import com.zitro.games.activate.test.presentation.common.PokemonListInput
import com.zitro.games.activate.test.presentation.common.XTPCNavRoutes
import com.zitro.games.activate.test.presentation.common.state.XTPCMviViewModel
import com.zitro.games.activate.test.presentation.common.state.XTPCUiSingleEvent
import com.zitro.games.activate.test.presentation.common.state.XTPCUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class XTPLoginViewModel @Inject constructor(
    private val xtdloginconverter: XTPLoginConverter,
    private val xtdLoginUseCase: XTDLoginUseCase,
    private val xtdSessionConverter: XTPSessionConverter,
    private val xtdSessionUseCase: XTDSessionUseCase
) : XTPCMviViewModel<XTPLoginModel, XTPCUiState<XTPLoginModel>, XTPLoginUiAction, XTPCUiSingleEvent>() {

    override fun initState(): XTPCUiState<XTPLoginModel> = XTPCUiState.Loading

    override fun handleAction(action: XTPLoginUiAction) {
        when(action){
            is XTPLoginUiAction.Load -> {
                loadSession(null)
            }

            is XTPLoginUiAction.LoginEmpty -> {
                submitState(XTPCUiState.Warning(action.message))
            }

            is XTPLoginUiAction.Login -> {
                submitState(XTPCUiState.Loading)
                loadLogin()
            }

            is XTPLoginUiAction.Access -> {
                submitSingleEvent(
                    XTPLoginUiSingleEvent.OpenSession(
                        XTPCNavRoutes.PokemonList.routeForPokemonList(
                            PokemonListInput(action.data.id ?: "")
                        )
                    )
                )
                submitState(XTPCUiState.NotInit)
            }
            else -> {}
        }
    }

    private fun loadSession(xtdSession: XTDSession?){
        viewModelScope.launch {
            xtdSessionUseCase.execute(XTDSessionUseCase.Request(xtdSession)).map {
                xtdSessionConverter.convert(it)
            }.collect { state ->
                if (state is XTPCUiState.Success){
                    if (state.data.id == ""){
                        submitState(XTPCUiState.NotInit)
                    } else {
                        submitState(state)
                    }
                }
            }
        }
    }

    private fun loadLogin(){
        viewModelScope.launch {
            xtdLoginUseCase.execute(XTDLoginUseCase.Request).map {
                xtdloginconverter.convert(it)
            }.collect { state ->
                if (state is XTPCUiState.Success){
                    state.data.id?.takeIf { id -> id.isNotEmpty()}?.let {
                        loadSession(XTDSession(it))
                    } ?: run {
                        XTPCUiState.Warning("No se pudo iniciar sesión")
                    }
                } else if (state is XTPCUiState.Error) {
                    submitState(state)
                }
            }
        }
    }
}