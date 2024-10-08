package com.sheverdyaevartem.hh.sign_in.data.remote_data_source

import com.sheverdyaevartem.hh.sign_in.data.dto.EmailVerifiedResponse
import retrofit2.http.GET


//plug
interface HHLoginApi {


    @GET("...plug")
    suspend fun verifyEmail(email: String): EmailVerifiedResponse
}