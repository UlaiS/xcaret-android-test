package com.xcaret.test.data.local.entity

import androidx.room.Entity

@Entity(tableName = "pokemon_list", primaryKeys = ["id"])
class XDTLPokemonListEntity(
    val id: Int,
    val count: Int?,
    val next: String?,
    val previous: String?,
    val results: List<XTDRPokemonEntity>
)

data class XTDRPokemonEntity(
    val name: String?,
    val url: String?,
    val id: String? = null,
){
    fun getNameCapitalize() = name?.replaceFirstChar(Char::titlecase)
    fun getImage() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${id}.png"
}