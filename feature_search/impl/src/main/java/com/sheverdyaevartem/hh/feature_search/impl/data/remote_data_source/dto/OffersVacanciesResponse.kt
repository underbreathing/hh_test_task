package com.sheverdyaevartem.hh.feature_search.impl.data.remote_data_source.dto

import com.google.gson.annotations.SerializedName
import com.sheverdyaevartem.hh.feature_search.api.domain.model.data.OfferDto
import com.sheverdyaevartem.hh.feature_search.api.domain.model.data.VacancyDto


data class OffersVacanciesResponse(
    @SerializedName("offers")
    val offerDtoList: List<OfferDto>?,
    @SerializedName("vacancies")
    val vacancyDtoList: List<VacancyDto>?
) : NetworkResponse()