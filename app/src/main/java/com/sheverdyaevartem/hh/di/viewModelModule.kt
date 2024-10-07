package com.sheverdyaevartem.hh.di

import com.sheverdyaevartem.hh.sign_in.ui.log_in.view_model.SignInViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        SignInViewModel(get())
    }
}