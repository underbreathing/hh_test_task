package com.sheverdyaevartem.hh.sign_in.domain.api

import com.sheverdyaevartem.hh.sign_in.domain.model.Resource
import kotlinx.coroutines.flow.Flow

interface NetworkRepository {

    suspend fun verifyEmail(email: String): Flow<Resource<Boolean>>

}