package com.sheverdyaevartem.hh.impl.data.impl

import com.sheverdyaevartem.hh.impl.data.dto.EmailVerifiedResponse
import com.sheverdyaevartem.hh.impl.data.dto.EmailVerifyRequest
import com.sheverdyaevartem.hh.impl.data.dto.NetworkResponse
import com.sheverdyaevartem.hh.impl.data.dto.SmsCodeVerifiedResponse
import com.sheverdyaevartem.hh.impl.data.dto.SmsCodeVerifyRequest
import com.sheverdyaevartem.hh.impl.data.remote_data_source.RemoteDataSource
import com.sheverdyaevartem.hh.feature_sign_in.api.domain.api.NetworkRepository
import com.sheverdyaevartem.hh.feature_sign_in.api.domain.model.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NetworkRepositoryImpl(private val remoteDataSource: RemoteDataSource) :
    NetworkRepository {
    override suspend fun verifyEmail(email: String): Flow<Resource<Boolean>> {
        return flow {
            val response: NetworkResponse = remoteDataSource.doRequest(EmailVerifyRequest(email))

            if (response is EmailVerifiedResponse) {
                emit(Resource.Answer(response.success))
            } else {
                emit(processResultCode(response.resultCode))
            }
        }
    }

    override suspend fun verifySmsCode(
        email: String,
        code: String
    ): Flow<Resource<Pair<Boolean, String>>> {
        return flow {
            val response = remoteDataSource.doRequest(SmsCodeVerifyRequest(email, code))

            if (response is SmsCodeVerifiedResponse) {
                emit(Resource.Answer(Pair(response.success, response.id)))
            } else {
                emit(processResultCode(response.resultCode))
            }
        }
    }

    private fun <T> processResultCode(resultCode: Int): Resource<T> {
        return when (resultCode) {
            -1 -> Resource.InternetError()
            400 -> Resource.RequestError()
            else -> Resource.ServerError()
        }
    }

}