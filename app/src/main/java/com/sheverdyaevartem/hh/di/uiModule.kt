package com.sheverdyaevartem.hh.di

import com.sheverdyaevartem.hh.feature_search.api.ui.mappers.OfferMapper
import com.sheverdyaevartem.hh.feature_search.api.ui.mappers.VacancyMapper
import org.koin.dsl.module

val uiModule = module {

    factory { OfferMapper() }

    factory { VacancyMapper() }
}