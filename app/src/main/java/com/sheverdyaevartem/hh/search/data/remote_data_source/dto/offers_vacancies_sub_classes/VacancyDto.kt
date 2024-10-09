package com.sheverdyaevartem.hh.search.data.remote_data_source.dto.offers_vacancies_sub_classes

import com.google.gson.annotations.SerializedName

data class VacancyDto(
    @SerializedName("address")
    val addressDto: AddressDto?,
    val appliedNumber: Int?,
    val company: String?,
    val description: String?,
    @SerializedName("experience")
    val experienceDto: ExperienceDto?,
    val id: String,
    val isFavorite: Boolean,
    val lookingNumber: Int?,
    val publishedDate: String?,
    val questions: List<String>?,
    val responsibilities: String?,
    @SerializedName("salary")
    val salaryDto: SalaryDto?,
    val schedules: List<String>?,
    val title: String
)