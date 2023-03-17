@file:Suppress("UNCHECKED_CAST")

package com.zitro.games.activate.test.presentation.login

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.xcaret.test.domain.usecase.XTDLoginUseCase
import com.zitro.games.activate.test.presentation.common.state.CommonScreen
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LoginScreen(
    viewModel: XTPLoginViewModel,
    navController: NavHostController
) {
    LaunchedEffect(Unit) {
        viewModel.submitAction(XTPLoginUiAction.Load)
    }

    Login<Any> {
        when(it){
            is XTDLoginUseCase.Request -> {
                viewModel.submitAction(XTPLoginUiAction.Login)
            }

            is String -> {
                viewModel.submitAction(XTPLoginUiAction.LoginEmpty(it))
            }
        }
    }

    viewModel.uiStateFlow.collectAsState().value.let { state ->
        CommonScreen(state = state) {
            viewModel.submitAction(XTPLoginUiAction.Access(it))
        }
    }

    LaunchedEffect(Unit) {
        viewModel.singleEventFlow.collectLatest {
            when (it) {
                is XTPLoginUiSingleEvent.OpenSession -> {
                    navController.navigate(it.navRoute)
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun<T> Login(
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
        .height(50.dp)
        .fillMaxWidth()
        .padding(start = 35.dp, end = 35.dp),
    login: (T)  -> Unit
) {
    var user by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    val keyboardController = LocalSoftwareKeyboardController.current

    with(LocalContext.current){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = getString(
                    R.string.img_content_logo
                ),
                modifier = Modifier.size(190.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = modifier.height(50.dp))

            TextField(
                value = user,
                onValueChange = { user = it },
                label = { Text(
                    getString(
                        R.string.et_user
                    )
                ) },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions (
                    onDone = {keyboardController?.hide()}
                ),
                modifier = modifier
            )

            Spacer(modifier = modifier.height(5.dp))

            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(
                    getString(
                        R.string.et_password
                    )
                ) },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions (
                    onDone = {keyboardController?.hide()}
                ),
                modifier = modifier
            )

            Spacer(modifier = modifier.height(5.dp))

            Button(
                onClick = {
                    if (user.isNotEmpty() && password.isNotEmpty()){
                        login.invoke(
                            XTDLoginUseCase.Request as T
                        )
                    } else {
                        login.invoke(
                            getString(
                                R.string.msg_empty_fields
                            ) as T
                        )
                    }
                },
                modifier = modifier,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
            ) {
                Text(
                    getString(
                        R.string.btn_sign_in
                    ),
                    color = MaterialTheme.colorScheme.primary,
                )
            }
        }
    }
}