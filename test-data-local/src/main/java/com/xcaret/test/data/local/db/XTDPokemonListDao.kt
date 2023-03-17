package com.xcaret.test.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.xcaret.test.data.local.entity.XDTLPokemonListEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface XTDPokemonListDao {
    @Query("SELECT * FROM pokemon_list")
    fun getPokemonList(): Flow<XDTLPokemonListEntity>

    @Query("SELECT * FROM pokemon_list WHERE id = :id")
    fun getPokemonList(id: Int): Flow<XDTLPokemonListEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPokemon(users: XDTLPokemonListEntity)
}