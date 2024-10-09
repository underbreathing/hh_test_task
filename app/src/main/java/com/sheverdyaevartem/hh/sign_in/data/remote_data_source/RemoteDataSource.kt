package com.sheverdyaevartem.hh.sign_in.data.remote_data_source

import com.sheverdyaevartem.hh.sign_in.data.dto.NetworkResponse

interface RemoteDataSource {
    suspend fun doRequest(dto: Any): NetworkResponse

}