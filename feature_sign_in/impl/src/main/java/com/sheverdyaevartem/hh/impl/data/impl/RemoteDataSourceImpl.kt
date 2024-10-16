package com.sheverdyaevartem.hh.impl.data.impl


import com.sheverdyaevartem.hh.impl.data.dto.EmailVerifiedResponse
import com.sheverdyaevartem.hh.impl.data.dto.EmailVerifyRequest
import com.sheverdyaevartem.hh.impl.data.dto.NetworkResponse
import com.sheverdyaevartem.hh.impl.data.dto.SmsCodeVerifiedResponse
import com.sheverdyaevartem.hh.impl.data.dto.SmsCodeVerifyRequest
import com.sheverdyaevartem.hh.impl.data.remote_data_source.RemoteDataSource
import com.sheverdyaevartem.hh.utils.network.ConnectivityVerifier

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteDataSourceImpl(
    private val connectivityVerifier: ConnectivityVerifier
) :
    RemoteDataSource {
    override suspend fun doRequest(dto: Any): NetworkResponse {
        return if (!connectivityVerifier.isConnected()) {
            NetworkResponse().apply { resultCode = -1 }
        } else {
            withContext(Dispatchers.IO) {
                try {
                    when (dto) {
                        is EmailVerifyRequest -> {
                            // val plugAnswer = hhService.verifyEmail(dto.email).apply { resultCode = 200 }
                            EmailVerifiedResponse(true).apply { resultCode = 200 }//hardcode
                        }

                        is SmsCodeVerifyRequest -> {
                            val hardcodedId = "1z4TbeDkbfXkvgpoJprXbN85uCcD7f00r"
                            SmsCodeVerifiedResponse(true, hardcodedId).apply { resultCode = 200 }
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