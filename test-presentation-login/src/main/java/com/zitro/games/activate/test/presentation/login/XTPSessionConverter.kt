package com.zitro.games.activate.test.presentation.login

import android.content.Context
import com.xcaret.test.domain.usecase.XTDSessionUseCase
import com.zitro.games.activate.test.presentation.common.state.XTPCommonResultConverter
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class XTPSessionConverter @Inject constructor(@ApplicationContext private val context: Context) :
    XTPCommonResultConverter<XTDSessionUseCase.Response, XTPLoginModel>() {

    override fun convertSuccess(data: XTDSessionUseCase.Response): XTPLoginModel {
        return XTPLoginModel(
            data.session.sessionId
        )
    }

}