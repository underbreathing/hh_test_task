package com.sheverdyaevartem.hh.di

import android.content.Context
import com.sheverdyaevartem.hh.utils.network.ConnectivityVerifier
import com.sheverdyaevartem.hh.feature_search.impl.data.remote_data_source.api.HHSearchApi
import com.sheverdyaevartem.hh.feature_search.impl.data.remote_data_source.api.SearchRemoteDataSource
import com.sheverdyaevartem.hh.feature_search.impl.data.remote_data_source.impl.SearchRemoteDataSourceImpl
import com.sheverdyaevartem.hh.feature_search.impl.data.remote_data_source.mappers.OfferDtoMapper
import com.sheverdyaevartem.hh.feature_search.impl.data.remote_data_source.mappers.VacancyDtoMapper
import com.sheverdyaevartem.hh.utils.impl.LocalStorageImpl
import com.sheverdyaevartem.hh.impl.data.impl.RemoteDataSourceImpl
import com.sheverdyaevartem.hh.utils.local_storage.LocalStorage
import com.sheverdyaevartem.hh.impl.data.remote_data_source.RemoteDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val SHARED_PREFERENCES_FILE = "shared_preferences_file"

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

    factory { (file: String) ->
        androidContext().getSharedPreferences(file, Context.MODE_PRIVATE)
    }

    single<LocalStorage> { LocalStorageImpl(get { parametersOf(SHARED_PREFERENCES_FILE) }) }

    single {
        OfferDtoMapper()
    }

    single {
        VacancyDtoMapper()
    }


}