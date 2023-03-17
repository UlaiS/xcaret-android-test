package com.zitro.games.activate.test.presentation.common.state

import com.xcaret.test.domain.entity.XTDResult

abstract class XTPCommonResultConverter<T : Any, R : Any> {

    fun convert(result: XTDResult<T>): XTPCUiState<R> {
        return when (result) {
            is XTDResult.Error -> {
                XTPCUiState.Error(result.exception.localizedMessage.orEmpty())
            }
            is XTDResult.Success -> {
                XTPCUiState.Success(convertSuccess(result.data))
            }
        }
    }

    abstract fun convertSuccess(data: T): R
}