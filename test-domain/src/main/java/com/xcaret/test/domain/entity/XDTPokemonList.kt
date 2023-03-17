package com.xcaret.test.domain.entity

data class XDTPokemonList(
    val id: Int,
    val count: Int?,
    val next: String?,
    val previous: String?,
    val results: List<XTDRPokemon>
)

data class XTDRPokemon(
    val name: String?,
    val url: String?,
    var id: String? = null,
){
    fun getNameCapitalize() = name?.replaceFirstChar(Char::titlecase)

    fun getImage(): String {
        id = url?.split("https://pokeapi.co/api/v2/pokemon/")
            ?.get(1)
            ?.split("/")
            ?.get(0)
         return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${id}.png"
    }
}