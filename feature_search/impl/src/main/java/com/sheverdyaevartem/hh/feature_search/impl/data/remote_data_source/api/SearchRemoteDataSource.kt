package com.sheverdyaevartem.hh.feature_search.impl.data.remote_data_source.api

import com.sheverdyaevartem.hh.feature_search.impl.data.remote_data_source.dto.NetworkResponse

interface SearchRemoteDataSource {
    suspend fun doRequest(dto: Any): NetworkResponse

}