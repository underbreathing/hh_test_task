package com.sheverdyaevartem.hh.search.data.remote_data_source.api

import com.sheverdyaevartem.hh.search.data.remote_data_source.dto.NetworkResponse

interface SearchRemoteDataSource {
    suspend fun doRequest(dto: Any): NetworkResponse

}