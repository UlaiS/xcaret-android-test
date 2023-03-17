package com.xcaret.test.data.local.injection

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.xcaret.test.data.local.db.XTDLAppDataBase
import com.xcaret.test.data.local.db.XTDLoginDao
import com.xcaret.test.data.local.db.XTDPokemonListDao
import com.xcaret.test.data.local.source.XTDLConstant.XTDL_NAME_DB
import com.xcaret.test.data.local.source.XTDLConstant.XTDL_NAME_PREFERENCE
import com.xcaret.test.data.local.source.XTDLocalSessionDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = XTDL_NAME_PREFERENCE)

@Module
@InstallIn(SingletonComponent::class)
class XTDLPersistenceModule {
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): XTDLAppDataBase =
        Room.databaseBuilder(
            context,
            XTDLAppDataBase::class.java, XTDL_NAME_DB
        ).build()

    @Provides
    fun provideLoginDao(appDatabase: XTDLAppDataBase): XTDLoginDao = appDatabase.login()

    @Provides
    fun providePokemonListDao(appDatabase: XTDLAppDataBase): XTDPokemonListDao = appDatabase.pokemonList()

    @Provides
    fun provideSessionDataSourceImpl(@ApplicationContext context: Context) =
        XTDLocalSessionDataSourceImpl(context.dataStore)
}