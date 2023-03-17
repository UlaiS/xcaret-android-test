package com.xcaret.test.data.local.entity

import androidx.room.Entity

@Entity(tableName = "login", primaryKeys = ["id"])
data class XTDLoginEntity(
    val id: String,
    val email: String?,
    val name: String?
)