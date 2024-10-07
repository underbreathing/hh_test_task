package com.sheverdyaevartem.hh.sign_in.ui.log_in.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sheverdyaevartem.hh.sign_in.domain.api.NetworkRepository
import com.sheverdyaevartem.hh.sign_in.domain.model.Resource
import com.sheverdyaevartem.hh.sign_in.ui.log_in.view_model.states.CodeSendState
import kotlinx.coroutines.launch

class SignInViewModel(private val networkRepository: NetworkRepository) : ViewModel() {

    private val _codeSend: SingleLiveEvent<CodeSendState> = SingleLiveEvent()

    val codeSend: LiveData<CodeSendState>
        get() = _codeSend

    fun emailLogin(enteredEmail: String) {
        viewModelScope.launch {
            networkRepository.verifyEmail(enteredEmail).collect { response ->
                when (response) {
                    is Resource.Answer -> {
                        if (response.data) {
                            _codeSend.value = CodeSendState.Success(enteredEmail)
                        } else {
                            _codeSend.value = CodeSendState.NotFound(enteredEmail)
                        }
                    }

                    is Resource.InternetError -> {
                        _codeSend.value = CodeSendState.InternetError
                    }

                    is Resource.RequestError -> {
                        _codeSend.value = CodeSendState.RequestError
                    }

                    is Resource.ServerError -> {
                        _codeSend.value = CodeSendState.ServerError
                    }
                }
            }
        }
    }


}