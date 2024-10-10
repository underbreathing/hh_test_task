package com.sheverdyaevartem.hh.impl.data.remote_data_source

import com.sheverdyaevartem.hh.impl.data.dto.NetworkResponse

interface RemoteDataSource {
    suspend fun doRequest(dto: Any): NetworkResponse

}