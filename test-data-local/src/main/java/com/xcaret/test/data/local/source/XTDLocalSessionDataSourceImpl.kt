package com.xcaret.test.data.local.source

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.xcaret.test.data.local.source.XTDLConstant.XTDL_NAME_PREFERENCE
import com.xcaret.test.data.repository.local.XTDRLocalSessionDataSource
import com.xcaret.test.domain.entity.XTDSession
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal val KEY_SESSION = stringPreferencesKey(XTDL_NAME_PREFERENCE)


class XTDLocalSessionDataSourceImpl(private val dataStore: DataStore<Preferences>):
    XTDRLocalSessionDataSource {
    override fun getSession(): Flow<XTDSession>  = dataStore.data.map {
        XTDSession(it[KEY_SESSION] ?: "")
    }

    override suspend fun saveSession(session: XTDSession) {
        dataStore.edit {
            it[KEY_SESSION] = session.sessionId ?: ""
        }
    }
}