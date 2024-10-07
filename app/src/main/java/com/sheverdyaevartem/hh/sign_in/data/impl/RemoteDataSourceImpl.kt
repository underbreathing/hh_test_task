package com.sheverdyaevartem.hh.sign_in.data.impl

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.sheverdyaevartem.hh.sign_in.data.dto.EmailVerifiedResponse
import com.sheverdyaevartem.hh.sign_in.data.dto.EmailVerifyRequest
import com.sheverdyaevartem.hh.sign_in.data.dto.NetworkResponse
import com.sheverdyaevartem.hh.sign_in.data.remote_data_source.HHApi
import com.sheverdyaevartem.hh.sign_in.data.remote_data_source.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteDataSourceImpl(private val context: Context) :
    RemoteDataSource {
    override suspend fun doRequest(dto: Any): NetworkResponse {
        return if (!isConnected()) {
            NetworkResponse().apply { resultCode = -1 }
        } else {
            withContext(Dispatchers.IO) {
                try {
                    if (dto is EmailVerifyRequest) {
                        // val plugAnswer = hhService.verifyEmail(dto.email).apply { resultCode = 200 }
                        EmailVerifiedResponse(true).apply { resultCode = 200 }//hardcode
                    } else {
                        NetworkResponse().apply { resultCode = 400 }
                    }
                } catch (e: Throwable) {
                    NetworkResponse().apply { resultCode = 500 }
                }
            }
        }
    }

    private fun isConnected(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
            }
        }
        return false
    }
}