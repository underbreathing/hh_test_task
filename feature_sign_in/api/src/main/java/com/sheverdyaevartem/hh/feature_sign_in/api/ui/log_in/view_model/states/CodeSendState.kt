package com.sheverdyaevartem.hh.feature_sign_in.api.ui.log_in.view_model.states

sealed interface CodeSendState {

    data class Success(val email: String) : CodeSendState

    data class NotFound(val email: String): CodeSendState
    data object ServerError : CodeSendState
    data object RequestError : CodeSendState
    data object InternetError : CodeSendState

}