package com.zitro.games.activate.test.presentation.login

import com.zitro.games.activate.test.presentation.common.state.XTPCUiSingleEvent


sealed class XTPLoginUiSingleEvent: XTPCUiSingleEvent {
    data class OpenSession(val navRoute: String): XTPLoginUiSingleEvent()
    data class CloseSession(val navRoute: String): XTPLoginUiSingleEvent()
}