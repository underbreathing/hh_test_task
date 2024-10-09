package com.sheverdyaevartem.hh.search.data.remote_data_source.dto

import com.google.gson.annotations.SerializedName
import com.sheverdyaevartem.hh.search.data.remote_data_source.dto.offers_vacancies_sub_classes.OfferDto


data class OffersVacanciesResponse(
    @SerializedName("offers")
    val offerDtos: List<OfferDto>,
    // val vacancies: List<Vacancy>
) : NetworkResponse()