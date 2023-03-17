package com.xcaret.test.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.xcaret.test.data.local.entity.XDTLPokemonListEntity
import com.xcaret.test.data.local.entity.XTDLoginEntity

@Database(entities = [
    XTDLoginEntity::class,
    XDTLPokemonListEntity::class,
], version = 1)
@TypeConverters(XTDLTypeConverter::class)
abstract class XTDLAppDataBase : RoomDatabase(){
    abstract fun login(): XTDLoginDao
    abstract fun pokemonList(): XTDPokemonListDao

}