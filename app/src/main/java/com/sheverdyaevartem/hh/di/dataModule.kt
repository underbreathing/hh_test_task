package com.sheverdyaevartem.hh.di

import com.sheverdyaevartem.hh.sign_in.data.impl.RemoteDataSourceImpl
import com.sheverdyaevartem.hh.sign_in.data.remote_data_source.RemoteDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {

    single<RemoteDataSource> {
        RemoteDataSourceImpl(androidContext())//TODO(добавить туда репозиторий когда он будет)
    }
}