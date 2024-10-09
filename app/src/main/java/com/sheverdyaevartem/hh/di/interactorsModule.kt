package com.sheverdyaevartem.hh.di

import com.sheverdyaevartem.hh.feature_search.api.domain.impl.SearchInteractorImpl
import com.sheverdyaevartem.hh.feature_search.api.domain.interactors.SearchInteractor
import org.koin.dsl.module

val interactorModule = module {

    factory<SearchInteractor> {
        SearchInteractorImpl(
            get(),
            get(),
            get()
        )
    }
}