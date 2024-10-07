package com.sheverdyaevartem.hh.sign_in.data.remote_data_source

import com.sheverdyaevartem.hh.sign_in.data.dto.EmailVerifiedResponse
import com.sheverdyaevartem.hh.sign_in.data.dto.EmailVerifyRequest
import retrofit2.http.GET


//plug
interface HHApi {


    @GET("...plug")
    suspend fun verifyEmail(email: String): EmailVerifiedResponse
}