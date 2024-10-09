package com.sheverdyaevartem.hh.search.data.remote_data_source.dto.offers_vacancies_sub_classes

import com.google.gson.annotations.SerializedName


data class OfferDto(
    @SerializedName("button")
    val buttonDto: ButtonDto?,
    val id: String?,
    val link: String?,
    val title: String
)