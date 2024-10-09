package com.sheverdyaevartem.hh.sign_in.ui.code_entry.view_model.model

sealed interface CodeVerifiedState {

    data class Answer(val isAccepted: Boolean) : CodeVerifiedState

    data object ServerError : CodeVerifiedState
    data object RequestError : CodeVerifiedState
    data object InternetError : CodeVerifiedState

}