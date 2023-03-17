@file:Suppress("UNCHECKED_CAST")

package com.zitro.games.activate.test.presentation.pokemon.list

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.xcaret.test.domain.entity.XTDRPokemon
import com.xcaret.test.domain.entity.ZDTPokemonListRequest
import com.zitro.games.activate.test.presentation.common.PokemonListInput
import com.zitro.games.activate.test.presentation.common.state.CommonScreen
import kotlinx.coroutines.flow.collectLatest


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonListScreen (
    viewModel: XTPPLPokemonsViewModel,
    pokemonInput: PokemonListInput,
    navController: NavController
) {

    val page = rememberSaveable { mutableStateOf(0) }
    val scroll = rememberLazyListState()
    val endOfListReached by remember {
        derivedStateOf {
            scroll.isScrolledToTheEnd()
        }
    }

    LaunchedEffect(Unit) {
        viewModel.submitAction(XTPPLPokemonsUiAction.Load)
    }

    var listPokemon: XTPPLPokemonsModel? = null
    viewModel.uiStateFlow.collectAsState().value.let { state ->
        CommonScreen(state = state) { list ->
            listPokemon = list
            Scaffold(
                content = {
                    LazyColumn(
                        state = scroll,
                        contentPadding = PaddingValues(horizontal = 10.dp),
                        verticalArrangement = Arrangement.spacedBy(5.dp),
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        itemsIndexed(items = list.pokemons.results){ i, p ->
                            PokemonItem(i, p)
                        }
                    }
                }
            )
        }
    }

    LaunchedEffect(endOfListReached) {
        page.value = page.value + 10
        viewModel.submitAction(XTPPLPokemonsUiAction.NextLoad(
            ZDTPokemonListRequest(
                limit = 10,
                page = page.value,
                list = listPokemon?.pokemons?.results ?: mutableListOf()
            )
        ))
    }

    LaunchedEffect(Unit) {
        viewModel.singleEventFlow.collectLatest {
            when (it) {
                is XTPPLPokemonsUiSingleEvent.CloseSession -> {
                    navController.navigate(it.navRoute)
                }
            }
        }
    }

}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PokemonItem(
    index: Int,
    pokemon: XTDRPokemon?,
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .border(width = 1.dp.div(2), color = MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .height(80.dp)
            .padding(5.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .clickable {

                }
                .padding(5.dp)
            ) {
                GlideImage(
                    model = pokemon?.getImage(),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(10.dp).fillMaxHeight(),
                )


                Text(
                    text = pokemon?.name.toString(),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

    }
}

fun LazyListState.isScrolledToTheEnd() =
    layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1
