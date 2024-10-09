package com.sheverdyaevartem.hh.sign_in.ui.code_entry.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sheverdyaevartem.hh.sign_in.domain.api.NetworkRepository
import com.sheverdyaevartem.hh.sign_in.domain.model.Resource
import com.sheverdyaevartem.hh.sign_in.ui.code_entry.view_model.model.CodeVerifiedState
import com.sheverdyaevartem.hh.sign_in.ui.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class CodeEntryViewModel(private val networkRepository: NetworkRepository) : ViewModel() {

    private val _codeVerified: SingleLiveEvent<CodeVerifiedState> = SingleLiveEvent()
    val codeVerified: LiveData<CodeVerifiedState>
        get() = _codeVerified

    fun verifySmsCode(email: String, code: String) {
        viewModelScope.launch {
            networkRepository.verifySmsCode(email, code).collect { data ->
                when (data) {
                    is Resource.Answer -> {
                        _codeVerified.value = CodeVerifiedState.Answer(data.data)
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