package com.sheverdyaevartem.hh.di

import com.sheverdyaevartem.hh.feature_search.impl.data.impl.SearchNetworkRepositoryImpl
import com.sheverdyaevartem.hh.feature_search.api.domain.repository.SearchNetworkRepository
import com.sheverdyaevartem.hh.feature_sign_in.api.domain.api.LocalRepository

import com.sheverdyaevartem.hh.impl.data.impl.NetworkRepositoryImpl
import com.sheverdyaevartem.hh.feature_sign_in.api.domain.api.NetworkRepository
import com.sheverdyaevartem.hh.impl.data.impl.LocalRepositoryImpl
import com.sheverdyaevartem.hh.feature_search.api.domain.repository.LocalRepository as LocalRepositorySearch
import com.sheverdyaevartem.hh.feature_search.impl.data.impl.LocalRepositoryImpl as LocalRepositorySearchImpl

import org.koin.dsl.module

val repositoryModule = module {

    single<NetworkRepository> {
        NetworkRepositoryImpl(get())
    }

    single<SearchNetworkRepository> {
        SearchNetworkRepositoryImpl(
            get(),
            get(),
            get()
        )
    }

    single<LocalRepository> { LocalRepositoryImpl(get()) }

    single<LocalRepositorySearch> { LocalRepositorySearchImpl(get()) }

}