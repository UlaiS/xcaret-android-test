package com.xcaret.test.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.xcaret.test.data.local.entity.XTDLoginEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface XTDLoginDao {
    @Query("SELECT * FROM login")
    fun getLogin(): Flow<XTDLoginEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLogin(users: XTDLoginEntity)
}