package com.sheverdyaevartem.hh.feature_search.impl.data.remote_data_source.impl

import com.sheverdyaevartem.hh.utils.network.ConnectivityVerifier
import com.sheverdyaevartem.hh.feature_search.impl.data.remote_data_source.api.HHSearchApi
import com.sheverdyaevartem.hh.feature_search.impl.data.remote_data_source.api.SearchRemoteDataSource
import com.sheverdyaevartem.hh.feature_search.impl.data.remote_data_source.dto.NetworkResponse
import com.sheverdyaevartem.hh.feature_search.impl.data.remote_data_source.dto.OffersVacanciesRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SearchRemoteDataSourceImpl(
    private val searchService: HHSearchApi,
    private val connectivityVerifier: ConnectivityVerifier
) : SearchRemoteDataSource {
    override suspend fun doRequest(dto: Any): NetworkResponse {
        return if (!connectivityVerifier.isConnected()) {
            NetworkResponse().apply { resultCode = -1 }
        } else {
            withContext(Dispatchers.IO) {
                try {
                    when (dto) {
                        is OffersVacanciesRequest -> {
                            searchService.getOffersAndVacancies(dto.id).apply { resultCode = 200 }
                        }

                        else -> {
                            NetworkResponse().apply { resultCode = 400 }
                        }
                    }
                } catch (e: Throwable) {
                    NetworkResponse().apply { resultCode = 500 }
                }
            }
        }
    }
}