package com.xcaret.test.domain.entity

sealed class XTDUseCaseException(cause: Throwable) : Throwable(cause) {

    class XTDLoginException(cause: Throwable) : XTDUseCaseException(cause)

    class XTDPokemonListException(cause: Throwable) : XTDUseCaseException(cause)

    class XTDUnknownException(cause: Throwable) : XTDUseCaseException(cause)

    companion object {

        fun createFromThrowable(throwable: Throwable): XTDUseCaseException {
            return if (throwable is XTDUseCaseException) throwable else XTDUnknownException(throwable)
        }
    }
}