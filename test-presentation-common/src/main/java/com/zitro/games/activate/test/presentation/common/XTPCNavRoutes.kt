package com.zitro.games.activate.test.presentation.common

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.navArgument

private const val XTPC_ROUTE_LOGIN = "login"
private const val XTPC_ROUTE_POKEMON_LIST = "/%s"

private const val XTPC_ARG_POKEMON_LIST = "userId"

sealed class XTPCNavRoutes(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList()
) {

    object Login : XTPCNavRoutes(route = String.format(XTPC_ROUTE_LOGIN)) {
        fun routeForLogin() = String.format(XTPC_ROUTE_LOGIN)
    }

    object PokemonList : XTPCNavRoutes(
        route = String.format(XTPC_ROUTE_POKEMON_LIST, "{$XTPC_ARG_POKEMON_LIST}"),
        arguments = listOf(navArgument(XTPC_ARG_POKEMON_LIST) {
            type = NavType.StringType
        })
    ) {

        fun routeForPokemonList(input: PokemonListInput) = String.format(XTPC_ROUTE_POKEMON_LIST, input.userId)

        fun fromEntry(entry: NavBackStackEntry): PokemonListInput {
            return PokemonListInput(entry.arguments?.getString(XTPC_ARG_POKEMON_LIST) ?: "")
        }
    }
}