package com.zitro.games.activate.test.presentation.login

import android.content.Context
import com.xcaret.test.domain.usecase.XTDLoginUseCase
import com.zitro.games.activate.test.presentation.common.state.XTPCommonResultConverter
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class XTPLoginConverter @Inject constructor(@ApplicationContext private val context: Context) :
    XTPCommonResultConverter<XTDLoginUseCase.Response, XTPLoginModel>() {

    override fun convertSuccess(data: XTDLoginUseCase.Response): XTPLoginModel {
        return XTPLoginModel(
            data.data.id
        )
    }

}