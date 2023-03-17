package com.xcaret.test.domain.entity

data class ZDTPokemonListRequest (
    val limit: Int,
    val page: Int,
    val list: List<XTDRPokemon>,
)