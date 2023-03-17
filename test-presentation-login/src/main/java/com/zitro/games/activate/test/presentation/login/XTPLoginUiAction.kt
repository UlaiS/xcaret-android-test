package com.zitro.games.activate.test.presentation.login

import com.zitro.games.activate.test.presentation.common.state.XTPCUiAction


sealed class XTPLoginUiAction: XTPCUiAction {
    object Load : XTPLoginUiAction()
    object Login : XTPLoginUiAction()
    data class LoginEmpty(val message: String) : XTPLoginUiAction()
    data class Access(val data: XTPLoginModel) : XTPLoginUiAction()
}