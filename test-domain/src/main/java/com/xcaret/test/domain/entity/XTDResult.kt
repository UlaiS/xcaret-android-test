package com.xcaret.test.domain.entity

sealed class XTDResult<out T : Any> {
    data class Success<out T : Any>(val data: T) : XTDResult<T>()
    class Error(val exception: XTDUseCaseException) : XTDResult<Nothing>()
}