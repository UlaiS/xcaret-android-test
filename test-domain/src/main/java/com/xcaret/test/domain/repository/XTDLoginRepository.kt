package com.xcaret.test.domain.repository

import com.xcaret.test.domain.entity.XTDLogin
import kotlinx.coroutines.flow.Flow

interface XTDLoginRepository {
    fun login(): Flow<XTDLogin>
}