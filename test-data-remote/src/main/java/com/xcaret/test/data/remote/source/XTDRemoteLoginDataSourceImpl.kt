package com.xcaret.test.data.remote.source

import com.google.gson.Gson
import com.xcaret.test.data.remote.networking.XTDRLoginApiModel
import com.xcaret.test.data.remote.networking.XTDRService
import com.xcaret.test.data.remote.source.XTDRLoginMock.mockLogin
import com.xcaret.test.data.repository.remote.XTDRemoteLoginDataSource
import com.xcaret.test.domain.entity.XTDLogin
import com.xcaret.test.domain.entity.XTDUseCaseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class XTDRemoteLoginDataSourceImpl @Inject constructor(private val service: XTDRService) :
    XTDRemoteLoginDataSource {

    override fun getLogin(): Flow<XTDLogin> = flow {
        emit(Gson().fromJson(mockLogin, XTDRLoginApiModel::class.java))
    }.map { data ->
        convert(data)
    }.catch {
        throw XTDUseCaseException.XTDLoginException(it)
    }

    private fun convert(login: XTDRLoginApiModel) = XTDLogin(
        id = login.id,
        email = login.email,
        name = login.name
    )
}