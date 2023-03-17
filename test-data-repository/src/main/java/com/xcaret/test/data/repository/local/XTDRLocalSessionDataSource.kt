package com.xcaret.test.data.repository.local

import com.xcaret.test.domain.entity.XTDSession
import kotlinx.coroutines.flow.Flow

interface XTDRLocalSessionDataSource {
    fun getSession(): Flow<XTDSession>
    suspend fun saveSession(session: XTDSession)
}