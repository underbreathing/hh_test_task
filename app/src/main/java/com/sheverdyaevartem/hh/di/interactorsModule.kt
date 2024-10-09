package com.sheverdyaevartem.hh.di

import com.sheverdyaevartem.hh.search.domain.impl.SearchInteractorImpl
import com.sheverdyaevartem.hh.search.domain.interactors.SearchInteractor
import com.sheverdyaevartem.hh.search.domain.mappers.OfferMapper
import org.koin.dsl.module

val interactorModule = module {

    factory<SearchInteractor> {
        SearchInteractorImpl(get(),get())
    }

    factory { OfferMapper() }
}