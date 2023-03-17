package com.xcaret.test.data.repository.repository

import com.xcaret.test.domain.entity.XTDSession
import com.xcaret.test.domain.repository.XTDSessionRepository
import com.xcaret.test.data.repository.local.XTDRLocalSessionDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class XTDRSessionRepositoryImpl @Inject constructor(
    private val localSessionDataSource: XTDRLocalSessionDataSource
): XTDSessionRepository {
    override fun getSession(): Flow<XTDSession> = localSessionDataSource.getSession()

    override fun saveSession(session: XTDSession): Flow<XTDSession> = flow {
        localSessionDataSource.saveSession(session)
        this.emit(Unit)
    }.flatMapLatest {
        getSession()
    }
}