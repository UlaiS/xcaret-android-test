package com.xcaret.test.domain.usecase

import com.xcaret.test.domain.entity.XTDResult
import com.xcaret.test.domain.entity.XTDUseCaseException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

abstract class XTDUseCase<I : XTDUseCase.Request, O : XTDUseCase.Response>(private val configuration: XTDConfiguration) {

    fun execute(request: I) = process(request)
        .map {
            XTDResult.Success(it) as XTDResult<O>
        }
        .flowOn(configuration.dispatcher)
        .catch {
            emit(XTDResult.Error(XTDUseCaseException.createFromThrowable(it)))
        }

    internal abstract fun process(request: I): Flow<O>

    class XTDConfiguration(val dispatcher: CoroutineDispatcher)

    interface Request

    interface Response
}