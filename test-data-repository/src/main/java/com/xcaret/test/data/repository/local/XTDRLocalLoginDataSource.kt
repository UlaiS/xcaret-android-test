package com.xcaret.test.data.repository.local

import com.xcaret.test.domain.entity.XTDLogin
import kotlinx.coroutines.flow.Flow

interface XTDRLocalLoginDataSource {
    fun getLogin(): Flow<XTDLogin>
    suspend fun setLogin(login: XTDLogin)
}