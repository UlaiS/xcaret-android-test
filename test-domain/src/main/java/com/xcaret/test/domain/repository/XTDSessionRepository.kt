package com.xcaret.test.domain.repository

import com.xcaret.test.domain.entity.XTDSession
import kotlinx.coroutines.flow.Flow

interface XTDSessionRepository {
    fun getSession(): Flow<XTDSession>
    fun saveSession(session: XTDSession): Flow<XTDSession>
}