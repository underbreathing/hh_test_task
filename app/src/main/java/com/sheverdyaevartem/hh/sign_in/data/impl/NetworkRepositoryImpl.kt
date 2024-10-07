package com.sheverdyaevartem.hh.sign_in.data.impl

import com.sheverdyaevartem.hh.sign_in.data.dto.EmailVerifiedResponse
import com.sheverdyaevartem.hh.sign_in.data.dto.EmailVerifyRequest
import com.sheverdyaevartem.hh.sign_in.data.dto.NetworkResponse
import com.sheverdyaevartem.hh.sign_in.data.remote_data_source.RemoteDataSource
import com.sheverdyaevartem.hh.sign_in.domain.api.NetworkRepository
import com.sheverdyaevartem.hh.sign_in.domain.model.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NetworkRepositoryImpl(private val remoteDataSource: RemoteDataSource) : NetworkRepository {
    override suspend fun verifyEmail(email: String): Flow<Resource<Boolean>> {
        return flow {
            val response: NetworkResponse = remoteDataSource.doRequest(EmailVerifyRequest(email))

            if (response is EmailVerifiedResponse) {
                emit(Resource.Answer(response.success))
            } else {
                when (response.resultCode) {
                    -1 -> emit(Resource.InternetError())
                    400 -> emit(Resource.RequestError())
                    500 -> emit(Resource.ServerError())
                }
            }
        }
    }


}