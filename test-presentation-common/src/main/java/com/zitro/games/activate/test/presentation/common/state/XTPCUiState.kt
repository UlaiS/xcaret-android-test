package com.zitro.games.activate.test.presentation.common.state

sealed class XTPCUiState<out T : Any> {
    object Loading : XTPCUiState<Nothing>()
    object NotInit : XTPCUiState<Nothing>()
    data class Warning(val warningMessage: String) : XTPCUiState<Nothing>()
    data class Error(val errorMessage: String) : XTPCUiState<Nothing>()
    data class Success<T : Any>(val data: T) : XTPCUiState<T>()
}