package com.sheverdyaevartem.hh.di

import com.sheverdyaevartem.hh.feature_search.api.ui.view_model.SearchViewModel
import com.sheverdyaevartem.hh.feature_sign_in.api.ui.code_entry.view_model.CodeEntryViewModel
import com.sheverdyaevartem.hh.feature_sign_in.api.ui.log_in.view_model.SignInViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        SignInViewModel(get())
    }

    viewModel {
        CodeEntryViewModel(get(),get())
    }

    viewModel {
        SearchViewModel(get(),get())
    }
}