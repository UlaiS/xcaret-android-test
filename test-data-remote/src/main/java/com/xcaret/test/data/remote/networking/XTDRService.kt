package com.xcaret.test.data.remote.networking

import com.xcaret.test.data.remote.source.XTDRUrl.XTDR_POKEMON_API_LIST
import retrofit2.http.GET
import retrofit2.http.Query

interface XTDRService {
    @GET(XTDR_POKEMON_API_LIST)
    suspend fun listPokemon(
        @Query("limit") limit : Int,
        @Query("offset") page : Int) : XTDRPokemonListApiModel
}