package com.sheverdyaevartem.hh.impl.data.remote_data_source

import com.sheverdyaevartem.hh.impl.data.dto.EmailVerifiedResponse
import retrofit2.http.GET


//plug
interface HHLoginApi {


    @GET("...plug")
    suspend fun verifyEmail(email: String): EmailVerifiedResponse
}