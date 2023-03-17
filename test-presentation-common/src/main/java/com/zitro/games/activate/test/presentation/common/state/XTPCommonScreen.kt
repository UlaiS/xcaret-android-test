package com.zitro.games.activate.test.presentation.common.state

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


@Composable
fun <T : Any> CommonScreen(state: XTPCUiState<T>, onSuccess: @Composable (T) -> Unit) {
    when (state) {
        is XTPCUiState.Loading -> {
            Loading()
        }
        is XTPCUiState.Error -> {
            Error(state.errorMessage)
        }
        is XTPCUiState.Success -> {
            onSuccess(state.data)
        }
        is XTPCUiState.Warning -> {
            Error(state.warningMessage)
        }
        else -> {

        }
    }
}

@Composable
fun Error(errorMessage: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {
        Snackbar {
            Text(text = errorMessage)
        }
    }
}

@Composable
fun Loading() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CircularProgressIndicator()
    }
}