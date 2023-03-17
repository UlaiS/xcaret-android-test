package com.xcaret.test.domain.usecase

import com.xcaret.test.domain.entity.XTDSession
import com.xcaret.test.domain.repository.XTDSessionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class XTDSessionUseCase(
    configuration: XTDConfiguration,
    private val xtdSessionRepository: XTDSessionRepository,
) : XTDUseCase<XTDSessionUseCase.Request, XTDSessionUseCase.Response>(configuration) {

    override fun process(request: Request): Flow<Response> =
        if (request.session != null){
            xtdSessionRepository.saveSession(request.session)
                .map {
                    Response(it)
                }
        } else {
            xtdSessionRepository.getSession().map {
                Response(it)
            }
        }

    data class Request(val session: XTDSession?) : XTDUseCase.Request

    data class Response(val session: XTDSession) : XTDUseCase.Response
}