package com.sheverdyaevartem.hh.feature_search.impl.data.remote_data_source.api

import com.sheverdyaevartem.hh.feature_search.impl.data.remote_data_source.dto.OffersVacanciesResponse
import retrofit2.http.GET
import retrofit2.http.Query


//plug
interface HHSearchApi {

    @GET("u/0/uc?export=download")
    suspend fun getOffersAndVacancies(
        @Query("id")
        fieldId: String
    ): OffersVacanciesResponse
}