package com.xcaret.test.data.local.source

import com.xcaret.test.data.local.db.XTDLoginDao
import com.xcaret.test.data.local.entity.XTDLoginEntity
import com.xcaret.test.data.repository.local.XTDRLocalLoginDataSource
import com.xcaret.test.domain.entity.XTDLogin
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class XTDLocalInfoLoginDataSourceImpl @Inject constructor(private val zgLoginDao: XTDLoginDao) :
    XTDRLocalLoginDataSource {


    override fun getLogin(): Flow<XTDLogin> = zgLoginDao.getLogin().map { info ->
        XTDLogin(
            id = info.id,
            email = info.email,
            name = info.name
        )
    }

    override suspend fun setLogin(login: XTDLogin) =
        zgLoginDao.insertLogin(
            XTDLoginEntity(
                id = login.id,
                email = login.email,
                name = login.name
            )
        )

}