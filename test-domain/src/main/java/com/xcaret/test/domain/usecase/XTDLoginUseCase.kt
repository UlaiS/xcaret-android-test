package com.xcaret.test.domain.usecase

import com.xcaret.test.domain.entity.XTDLogin
import com.xcaret.test.domain.repository.XTDLoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class XTDLoginUseCase(
    configuration: XTDConfiguration,
    private val xtdLoginRepository: XTDLoginRepository,
) : XTDUseCase<XTDLoginUseCase.Request, XTDLoginUseCase.Response>(configuration) {

    override fun process(request: Request): Flow<Response> =
        xtdLoginRepository.login()
            .map {
                Response(it)
            }
    
    object Request : XTDUseCase.Request

    data class Response(val data: XTDLogin) : XTDUseCase.Response
}