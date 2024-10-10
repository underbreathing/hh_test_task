package com.sheverdyaevartem.hh.di

import com.sheverdyaevartem.hh.utils.network.ConnectivityVerifier
import com.sheverdyaevartem.hh.feature_search.impl.data.remote_data_source.api.HHSearchApi
import com.sheverdyaevartem.hh.feature_search.impl.data.remote_data_source.api.SearchRemoteDataSource
import com.sheverdyaevartem.hh.feature_search.impl.data.remote_data_source.impl.SearchRemoteDataSourceImpl
import com.sheverdyaevartem.hh.feature_search.impl.data.remote_data_source.mappers.OfferDtoMapper
import com.sheverdyaevartem.hh.feature_search.impl.data.remote_data_source.mappers.VacancyDtoMapper
import com.sheverdyaevartem.hh.impl.data.impl.RemoteDataSourceImpl
import com.sheverdyaevartem.hh.impl.data.remote_data_source.RemoteDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {

    single {
        Retrofit.Builder().baseUrl("https://drive.usercontent.google.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(HHSearchApi::class.java)
    }

    single { ConnectivityVerifier(androidContext()) }

    single<SearchRemoteDataSource> {
        SearchRemoteDataSourceImpl(
            get(),
            get()
        )
    }

    single<RemoteDataSource> {
        RemoteDataSourceImpl(get())
    }

    single {
        OfferDtoMapper()
    }

    single {
        VacancyDtoMapper()
    }


}