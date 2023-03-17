package com.xcaret.test.domain.usecase

import com.xcaret.test.domain.entity.XDTPokemonList
import com.xcaret.test.domain.entity.ZDTPokemonListRequest
import com.xcaret.test.domain.repository.XTDPokemonListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flattenConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class XTDPokemonListUseCase(
    configuration: XTDConfiguration,
    private val xtdPokemonListRepository: XTDPokemonListRepository,
) : XTDUseCase<XTDPokemonListUseCase.Request, XTDPokemonListUseCase.Response>(configuration) {

    override fun process(request: Request): Flow<Response> {
        val response = xtdPokemonListRepository.pokemonLocalList(request.pokemonRequest).map { local ->
            return@map if(local != null) flow { emit(local) }.map { Response(it) } else xtdPokemonListRepository.pokemonList (
                ZDTPokemonListRequest(
                    request.pokemonRequest.limit, request.pokemonRequest.page, request.pokemonRequest.list
                )
            ).map {
                Response(it)
            }
        }.flattenConcat()

        return response
    }

    data class Request(val pokemonRequest: ZDTPokemonListRequest) : XTDUseCase.Request

    data class Response(val pokemonResponse: XDTPokemonList) : XTDUseCase.Response
}