package com.xcaret.test.data.repository.remote

import com.xcaret.test.domain.entity.XTDLogin
import kotlinx.coroutines.flow.Flow

interface XTDRemoteLoginDataSource {
    fun getLogin(): Flow<XTDLogin>
}