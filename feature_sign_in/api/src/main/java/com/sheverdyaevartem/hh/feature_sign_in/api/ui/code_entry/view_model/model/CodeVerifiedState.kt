package com.sheverdyaevartem.hh.feature_sign_in.api.ui.code_entry.view_model.model

sealed interface CodeVerifiedState {

    data class Answer(val isAccepted: Boolean, val id: String) : CodeVerifiedState

    data object ServerError : CodeVerifiedState
    data object RequestError : CodeVerifiedState
    data object InternetError : CodeVerifiedState

}