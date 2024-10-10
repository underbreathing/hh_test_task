package com.sheverdyaevartem.hh.di

import com.sheverdyaevartem.hh.feature_search.impl.data.repository.SearchNetworkRepositoryImpl
import com.sheverdyaevartem.hh.feature_search.api.domain.repository.SearchNetworkRepository
import com.sheverdyaevartem.hh.sign_in.data.impl.NetworkRepositoryImpl
import com.sheverdyaevartem.hh.sign_in.domain.api.NetworkRepository
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
}