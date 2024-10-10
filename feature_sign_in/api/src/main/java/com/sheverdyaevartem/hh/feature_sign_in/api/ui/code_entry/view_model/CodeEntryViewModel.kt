package com.sheverdyaevartem.hh.feature_sign_in.api.ui.code_entry.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sheverdyaevartem.hh.feature_sign_in.api.domain.api.LocalRepository
import com.sheverdyaevartem.hh.feature_sign_in.api.domain.api.NetworkRepository
import com.sheverdyaevartem.hh.feature_sign_in.api.domain.model.Resource


import com.sheverdyaevartem.hh.feature_sign_in.api.ui.code_entry.view_model.model.CodeVerifiedState
import com.sheverdyaevartem.hh.utils.view_model.SingleLiveEvent
import kotlinx.coroutines.launch

class CodeEntryViewModel(
    private val networkRepository: NetworkRepository,
    private val localRepository: LocalRepository
) : ViewModel() {

    private val _codeVerified: SingleLiveEvent<CodeVerifiedState> = SingleLiveEvent()
    val codeVerified: LiveData<CodeVerifiedState>
        get() = _codeVerified

    fun verifySmsCode(email: String, code: String) {
        viewModelScope.launch {
            networkRepository.verifySmsCode(email, code).collect { data ->
                when (data) {
                    is Resource.Answer -> {
                        localRepository.safeId(data.data.second)
                        _codeVerified.value =
                            CodeVerifiedState.Answer(data.data.first)
                    }

                    is Resource.InternetError ->
                        _codeVerified.value = CodeVerifiedState.InternetError

                    is Resource.RequestError -> _codeVerified.value = CodeVerifiedState.RequestError
                    is Resource.ServerError -> _codeVerified.value = CodeVerifiedState.ServerError
                }
            }
        }
    }


}