package com.xcaret.test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.xcaret.test.ui.theme.XcaretTestTheme
import com.zitro.games.activate.test.presentation.common.XTPCNavRoutes
import com.zitro.games.activate.test.presentation.login.LoginScreen
import com.zitro.games.activate.test.presentation.pokemon.list.PokemonListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            XcaretTestTheme {
                Surface(color = MaterialTheme.colorScheme.surface) {
                    val navController = rememberNavController()
                    App(navController = navController)
                }
            }
        }
    }

    @Composable
    fun App(navController: NavHostController) {
        NavHost(navController, startDestination = XTPCNavRoutes.Login.route) {
            composable(route = XTPCNavRoutes.Login.route) {
                LoginScreen(hiltViewModel(), navController)
            }

            composable(
                route = XTPCNavRoutes.PokemonList.route,
                arguments = XTPCNavRoutes.PokemonList.arguments
            ) {
                PokemonListScreen(
                    hiltViewModel(),
                    XTPCNavRoutes.PokemonList.fromEntry(it),
                    navController
                )
            }
        }
    }
}