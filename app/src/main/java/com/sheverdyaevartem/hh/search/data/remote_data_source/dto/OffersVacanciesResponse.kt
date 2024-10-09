package com.sheverdyaevartem.hh.search.data.remote_data_source.dto

import com.google.gson.annotations.SerializedName
import com.sheverdyaevartem.hh.search.data.remote_data_source.dto.offers_vacancies_sub_classes.OfferDto
import com.sheverdyaevartem.hh.search.data.remote_data_source.dto.offers_vacancies_sub_classes.VacancyDto


data class OffersVacanciesResponse(
    @SerializedName("offers")
    val offerDtoList: List<OfferDto>?,
    @SerializedName("vacancies")
    val vacancyDtoList: List<VacancyDto>?
) : NetworkResponse()