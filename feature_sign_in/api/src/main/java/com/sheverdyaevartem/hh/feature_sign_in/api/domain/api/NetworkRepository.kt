package com.sheverdyaevartem.hh.feature_sign_in.api.domain.api

import com.sheverdyaevartem.hh.feature_sign_in.api.domain.model.Resource
import kotlinx.coroutines.flow.Flow

interface NetworkRepository {

    suspend fun verifyEmail(email: String): Flow<Resource<Boolean>>

    suspend fun verifySmsCode(email: String, code: String): Flow<Resource<Pair<Boolean, String>>>

}