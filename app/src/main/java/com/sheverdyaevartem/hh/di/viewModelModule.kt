package com.sheverdyaevartem.hh.di

import com.sheverdyaevartem.hh.search.ui.view_model.SearchViewModel
import com.sheverdyaevartem.hh.sign_in.ui.code_entry.view_model.CodeEntryViewModel
import com.sheverdyaevartem.hh.sign_in.ui.log_in.view_model.SignInViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        SignInViewModel(get())
    }

    viewModel {
        CodeEntryViewModel(get())
    }

    viewModel { (id: String) ->
        SearchViewModel(id, get())
    }
}