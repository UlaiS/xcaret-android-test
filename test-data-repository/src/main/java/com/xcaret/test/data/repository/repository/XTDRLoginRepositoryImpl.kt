package com.xcaret.test.data.repository.repository

import com.xcaret.test.data.repository.local.XTDRLocalLoginDataSource
import com.xcaret.test.data.repository.remote.XTDRemoteLoginDataSource
import com.xcaret.test.domain.entity.XTDLogin
import com.xcaret.test.domain.repository.XTDLoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class XTDRLoginRepositoryImpl @Inject constructor(
    private val localLoginDataSource: XTDRLocalLoginDataSource,
    private val remoteLoginDataSource: XTDRemoteLoginDataSource
): XTDLoginRepository {
    override fun login(): Flow<XTDLogin> =
        remoteLoginDataSource.getLogin().onEach {
            localLoginDataSource.setLogin(it)
        }
}